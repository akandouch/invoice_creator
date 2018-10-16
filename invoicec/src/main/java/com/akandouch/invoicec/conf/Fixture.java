package com.akandouch.invoicec.conf;

import com.akandouch.invoicec.domain.*;
import com.akandouch.invoicec.repository.InvoiceProfileRepository;
import com.akandouch.invoicec.repository.InvoiceRepository;
import com.akandouch.invoicec.repository.ItemRepository;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

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
                        .firstname("Michael")
                        .lastname("Bay")
                        .vat("0123456789")
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
                        .firstname("BNP")
                        .lastname("Paribas")
                        .vat("9876543210")
                        .build()
        );
        LOGGER.info("create item");
        Item item = itemRepository.save(Item.builder()
                .id(RandomUtils.nextLong())
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
                .build()
        );
        LOGGER.info("create invoice");
        invoiceRepository.save(Invoice.builder()
                .items(Arrays.asList(item))
                .invoiced(invoiced)
                .invoicer(invoicer)
                .title("Facture #1")
                .build()
        );
        LOGGER.info("end adding fixtures...");

    }
}
