package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;



    public static void main(String[] args) throws IOException {

       var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
               generator.run();
    }

    private void run() throws IOException {
        var date = generate();
        save(date);
    }


    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {

         throw new IllegalArgumentException("Неизвестный тип данных" + type);
         }
    }

    private Object generateDate(Supplier<Object> dataSupplier) {
        return  Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());

    }

    private Object generateContacts() {
        return generateDate(() -> new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withMiddleName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withTelephoneHome(CommonFunctions.randomString(10))
                .withTelephoneMobile(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10)));


    }

    private Object generateGroups() {
        return generateDate(() -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }

    private void save(Object date) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(date);
           try (var writer = new FileWriter(output)) {
               writer.write(json);
           }

        } if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), date);
        } if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), date);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }

    }

//    private void save(Object date) throws IOException {
//        if ("json".equals(format)) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            mapper.writeValue(new File(output), date);
//        } else {
//            throw new IllegalArgumentException("Неизвестный формат данных " + format);
//        }
//
//    }
}
