package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.MethodSource;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//        for (var name : List.of("", "group name")) {
//            for (var header : List.of("", "group header")) {
//                for (var footer : List.of("","group footer")) {
//                    result.add(new GroupData().withName(name).withFooter(header).withHeader(footer));
//                }
//            }
//        }

        var json = "";
        try (var reader = new FileReader("groups.json");
             var breader = new BufferedReader(reader)
        ){
            var line =  breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();

            }
        }
//        var json= Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {});
        result.addAll(value);
        return result;
    }


    public static Stream<GroupData> RandomGroups()  {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30));

      return   Stream.generate(randomGroup).limit(1);
    }

    public static List<GroupData> singleRandomGroup1()  {
        return   List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30)));
    }



    @ParameterizedTest
    @MethodSource("RandomGroups")
    public void canCreateGroup3(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        var extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));

//        var newUiGroups = app.groups().getList(); тут можно сделать проверку сравнения список с веб интерфейса и спосок из базы
    }

//    @ParameterizedTest
//    @MethodSource("singleRandomGroup")
//    public void canCreateGroup2(GroupData group) {
//        var oldGroups = app.hbm().getGroupList();
//        app.groups().createGroup(group);
//
//        var newGroups = app.hbm().getGroupList();
//        Comparator<GroupData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newGroups.sort(compareById);
//        var maxId = newGroups.get(newGroups.size() - 1).id();
//        var expectedList = new ArrayList<>(oldGroups);
//        expectedList.add(group.withId(maxId));
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newGroups , expectedList);
//
////        var newUiGroups = app.groups().getList(); тут можно сделать проверку сравнения список с веб интерфейса и спосок из базы
//    }

//    @ParameterizedTest
//    @MethodSource("singleRandomGroup")
//    public void canCreateGroup(GroupData group) {
//        var oldGroups = app.jdbc().getGroupList();
//        app.groups().createGroup(group);
//
//        var newGroups = app.jdbc().getGroupList();
//        Comparator<GroupData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newGroups.sort(compareById);
//        var maxId = newGroups.get(newGroups.size() - 1).id();
//        var expectedList = new ArrayList<>(oldGroups);
//        expectedList.add(group.withId(maxId));
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newGroups , expectedList);
//
////        var newUiGroups = app.groups().getList(); тут можно сделать проверку сравнения список с веб интерфейса и спосок из базы
//    }



    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();

        app.groups().createGroup(group);

        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups , expectedList);
    }



    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of (

                new GroupData ("", "group name'", "", "")));

        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups , oldGroups);
    }
}
