package com.akandouch.invoicec.conf;

import com.akandouch.invoicec.domain.*;
import com.akandouch.invoicec.repository.InvoiceProfileRepository;
import com.akandouch.invoicec.repository.InvoiceRepository;
import com.akandouch.invoicec.repository.ItemRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

@Configuration
@Profile("dev")
public class Fixture implements CommandLineRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(Fixture.class);

    private final InvoiceProfileRepository invoiceProfileRepository;
    private final ItemRepository itemRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public Fixture(InvoiceProfileRepository invoiceProfileRepository, ItemRepository itemRepository, InvoiceRepository invoiceRepository) {
        this.invoiceProfileRepository = invoiceProfileRepository;
        this.itemRepository = itemRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("dev mode, cleanup mongodb");

        itemRepository.deleteAll();
        invoiceRepository.deleteAll();
        invoiceProfileRepository.deleteAll();

        LOGGER.info("dev mode, create fixture");

        LOGGER.info("create invoicer profile");

        File f = new File(getClass().getClassLoader().getResource("img/logo_fixture.png").getFile());

        byte[]b = Files.readAllBytes(Paths.get(f.getPath()));

        String logo = "data:image/png;base64," + Base64.getEncoder().encodeToString(b);

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
                        .logo(logo.toCharArray())
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
                .id(RandomStringUtils.randomAlphanumeric(12))
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
        LOGGER.info("create invoice");
        invoiceRepository.save(Invoice.builder()
                .items(Arrays.asList(item))
                .invoiced(invoiced)
                .invoicer(invoicer)
                .title("Facture #1")
                .status(0)
                .build()
        );
        LOGGER.info("end adding fixtures...");

    }
}
