package com.akandouch.invoicec.conf;

import com.akandouch.invoicec.domain.*;
import com.akandouch.invoicec.repository.InvoiceProfileRepository;
import com.akandouch.invoicec.repository.InvoiceRepository;
import com.akandouch.invoicec.repository.ItemRepository;
import com.akandouch.invoicec.repository.SettingsRepository;
import com.akandouch.invoicec.service.InvoiceService;
import com.akandouch.invoicec.service.SettingsService;
import com.akandouch.invoicec.service.UploadService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.stream.IntStream;

@Configuration
@Profile("dev")
public class Fixture implements CommandLineRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(Fixture.class);

    private final InvoiceProfileRepository invoiceProfileRepository;
    private final ItemRepository itemRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceService invoiceService;
    private final SettingsRepository settingsRepository;
    private final SettingsService settingsService;
    private final UploadService uploadService;
    private final Environment environment;

    @Autowired
    public Fixture(InvoiceProfileRepository invoiceProfileRepository, ItemRepository itemRepository, InvoiceRepository invoiceRepository, InvoiceService invoiceService, SettingsRepository settingsRepository, SettingsService settingsService, UploadService uploadService, Environment environment) {
        this.invoiceProfileRepository = invoiceProfileRepository;
        this.itemRepository = itemRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceService = invoiceService;
        this.settingsRepository = settingsRepository;
        this.settingsService = settingsService;
        this.uploadService = uploadService;
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("dev mode, cleanup mongodb");
        Integer numberOfInvoices = environment.getProperty("invoicer.fixture.invoice.number", Integer.class);
        uploadService.deleteAll();
        itemRepository.deleteAll();
        invoiceRepository.deleteAll();
        invoiceProfileRepository.deleteAll();
        settingsRepository.deleteAll();
        LOGGER.info("dev mode, create fixture");

        LOGGER.info("create default settings");
        settingsService.saveOrUpdateSettings(settingsService.getSettings().toBuilder().currentRate(500f).build());

        LOGGER.info("create invoicer profile");

        InputStream logoStream = new ClassPathResource("img/logo_fixture.png").getInputStream();
        InputStream pdf = new ClassPathResource("pdf/somepdf.pdf").getInputStream();

        byte[] logoBytes = IOUtils.toByteArray(logoStream);
        byte[] pdfBytes = IOUtils.toByteArray(pdf);

        Upload uploadLogo = uploadService.save(logoBytes, "image/png", "logo.png");
        LOGGER.info("logo saved with id : " + uploadLogo.getId());

        Upload uploadPdf = uploadService.save(pdfBytes, "application/pdf", "somepdf.pdf");

        LOGGER.info("pdf saved with id : " + uploadLogo.getId());

        InvoiceProfile invoicer = invoiceProfileRepository.save(
                InvoiceProfile.builder()
                        .address(Address.builder()
                                .city("Tervuren")
                                .country("Belgique")
                                .postcode("3080")
                                .street("rue de la street")
                                .streetNumber("88")
                                .build()
                        )
                        .active(Boolean.TRUE)
                        .accountNumber("BE 99 876 455 7478")
                        .firstname("Michael")
                        .mail("john@doe.fr")
                        .phoneNumber("0487/34.34.34")
                        .lastname("Bay")
                        .vat("0123456789")
                        .logo(uploadLogo)
                        .build()
        );
        LOGGER.info("create invoiced profile");
        InvoiceProfile invoiced = invoiceProfileRepository.save(
                InvoiceProfile.builder()
                        .address(Address.builder()
                                .city("Las Vegas")
                                .country("United states")
                                .postcode("90000")
                                .street("casino boulevard")
                                .streetNumber("21")
                                .build()
                        )
                        .active(Boolean.FALSE)
                        .customer(true)
                        .mail("foo@bar.fr")
                        .phoneNumber("0498/13.12.14")
                        .accountNumber("BE 33 332 223 2223")
                        .firstname("BNP")
                        .lastname("Paribas")
                        .vat("9876543210")
                        .build()
        );
        LOGGER.info("create item");
        Item item = itemRepository.save(Item.builder()
                .days(20f)
                .amount(10000d)
                .description("BNP Project")
                .nature("Consulting")
                .project("BNPPF-7282")
                .period(Period.builder()
                        .from(DateDto.builder().day(1).month(12).year(2019).build())
                        .to(DateDto.builder().day(31).month(12).year(2019).build())
                        .build()
                )
                .days(20f)
                .rate(500f)
                .unit(2)
                .vatRate(0.21f)
                .build()
        );
        LOGGER.info(String.format("create %s invoices", numberOfInvoices));
        if (numberOfInvoices == null || numberOfInvoices > 12) {
            LOGGER.error("property is null or MAX 12 invoices for now!");
            throw new RuntimeException("todo make it work with more than 12 invoices");
        }
        int year = LocalDate.now().getYear();
        IntStream.range(0, numberOfInvoices)
                .mapToObj(i -> (Invoice.builder()
                        .attachments(Arrays.asList(uploadPdf))
                        .items(Arrays.asList(item.toBuilder()
                                .rate(RandomUtils.nextLong(100, 750) + 0f)
                                .days(RandomUtils.nextLong(15,22) + 0f)
                                .period(Period.builder()
                                        .from(DateDto.builder()
                                                .day(1)
                                                .month(YearMonth.of(Year.now().getValue(), i + 1).getMonthValue())
                                                .year(year)
                                                .build())
                                        .to(DateDto.builder()
                                                .day(YearMonth.of(Year.now().getValue(), i + 1).atEndOfMonth().getDayOfMonth())
                                                .month(YearMonth.of(Year.now().getValue(), i + 1).getMonthValue())
                                                .year(year)
                                                .build())
                                        .build()
                        ).build()))
                        .invoiced(invoiced)
                        .invoicer(invoicer)
                        .title("Facture #" + i+1)
                        .status(i > 0 ? 2 : 0)
                        .build()))
                .forEach(invoiceService::save);
        LOGGER.info("end adding fixtures...");

    }
}
