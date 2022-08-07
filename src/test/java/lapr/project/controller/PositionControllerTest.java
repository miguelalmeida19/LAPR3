package lapr.project.controller;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import lapr.project.model.Message;
import lapr.project.model.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

public class PositionControllerTest {

    public ShipController shipController = new ShipController();
    public PositionController positionController = new PositionController();

    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {

        shipController.importShips("files/testShip.csv");
    }

    @Test
    public void testDatePosition() {
        assertThrows(IllegalArgumentException.class,
                () -> (new PositionController()).DatePosition("2020/03/01", "Hour", "foo"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).DatePosition(" ", "Hour", "foo"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).DatePosition("", "Hour", "foo"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).DatePosition("/", "Hour", "foo"));
    }

    @Test
    public void testDatePosition1() {
        String Date = "31/12/2020";
        String Hour = "16:15";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dt = LocalDateTime.parse(Date + " " + Hour, formatter);
        LocalDateTime localDateTime = positionController.dateTime(Date + " " + Hour);
        assertEquals(dt, localDateTime);

        Ship ship = App.getInstance().getCompany().getShipStore().getShip("211331640");

        LocalDateTime dt1 = LocalDateTime.parse("31/12/2020 16:15", formatter);
        Message mes1 = new Message(dt1, 33.95038, -119.08169, 11.4, 119, 119, "B");

        LocalDateTime dt2 = LocalDateTime.parse("31/12/2020 18:03", formatter);
        Message mes2 = new Message(dt2, 33.78012, -118.72088, 11.6, 120, 121, "B");

        LocalDateTime dt3 = LocalDateTime.parse("31/12/2020 00:39", formatter);
        Message mes3 = new Message(dt3, 36.59348, -122.86674, 16.4, 147.6, 149, "B");

        LocalDateTime dt4 = LocalDateTime.parse("31/12/2020 01:25", formatter);
        Message mes4 = new Message(dt4, 36.39094, -122.71335, 19.7, 145.5, 147, "B");

        TreeSet<Message> messages = new TreeSet<>();
        messages.add(mes1);
        messages.add(mes2);
        messages.add(mes3);
        messages.add(mes4);

        TreeSet<Message> messageTreeSet = ship.getAllMessages();

        Assert.assertEquals(messages, messageTreeSet);
        Assert.assertNotEquals(0, messageTreeSet.size());

        List<String> actualList = positionController.DatePosition(Date, Hour, "211331640");
        assertNotEquals(Collections.emptyList(), actualList);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format("%-20s %-20s %-20s %-20s %-20s",
                ship.getName(), Date, Hour, mes1.getLatitude(), mes1.getLongitude()));

        for (String s: actualList){
            assertTrue(s.contains(String.valueOf(mes1.getLatitude())) && s.contains(String.valueOf(mes1.getLongitude())));
        }

        assertEquals(expectedList, actualList);
    }


    @Test
    public void testPeriodPosition() {
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("2020/03/01", "", "2020-03-01", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("", "", "", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("00/12/2030", "", "00/12/2030", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("81/12/2020", "", "81/12/2020", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("18/14/2020", "", "18/14/2020", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("18/00/2020", "", "18/00/2020", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("18/12/2030", "", "18/12/2030", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("18/12/2023", "", "18/12/2023", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("00/12/2010", "", "00/12/2010", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("32/12/2010", "", "32/12/2010", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("18/13/2010", "", "18/13/2010", "", ""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).PeriodPosition("18/12/1999", "", "18/12/1999", "", ""));

    }

    @Test
    public void testPeriodPosition1() {
        String Date = "31/12/2020";
        String Hour = "16:15";
        String Date1 = "31/12/2020";
        String Hour1 = "18:03";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dt = LocalDateTime.parse(Date + " " + Hour, formatter);
        LocalDateTime localDateTime = positionController.dateTime(Date + " " + Hour);
        assertEquals(dt, localDateTime);

        LocalDateTime dtr = LocalDateTime.parse(Date1 + " " + Hour1, formatter);
        LocalDateTime localDateTime1 = positionController.dateTime(Date1 + " " + Hour1);
        assertEquals(dtr, localDateTime1);
    }

    @Test
    public void testPeriodPosition2() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date cannot be blank.");

        String Date = "";
        String Hour = "";
        String Date1 = "";
        String Hour1 = "";

        positionController.PeriodPosition(Date,Hour,Date1,Hour1,"211334640");
        assertFalse(Date.equals(""));
        assertFalse(Date1.equals(""));
        assertFalse(Hour.equals(""));
        assertFalse(Hour1.equals(""));
    }

    @Test
    public void testPeriodPosition3() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date cannot be blank.");

        String Date = "12/12/2020";
        String Hour = "12:20";
        String Date1 = "";
        String Hour1 = "";

        positionController.PeriodPosition(Date,Hour,Date1,Hour1,"211334640");
    }

    @Test
    public void testPeriodPosition4() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Hour cannot be blank.");

        String Date = "12/12/2020";
        String Hour = "";
        String Date1 = "";
        String Hour1 = "";

        positionController.PeriodPosition(Date,Hour,Date1,Hour1,"211334640");
    }

    @Test
    public void testPeriodPosition5() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Hour cannot be blank.");

        String Date = "12/12/2020";
        String Hour = "12:20";
        String Date1 = "12/03/2020";
        String Hour1 = "";

        positionController.PeriodPosition(Date,Hour,Date1,Hour1,"211334640");
    }

    @Test
    public void testPeriodPosition6(){
        String Date = "31/12/2020";
        String Hour = "16:15";
        String Date1 = "31/12/2020";
        String Hour1 = "18:03";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dt = LocalDateTime.parse(Date + " " + Hour, formatter);
        LocalDateTime localDateTime = positionController.dateTime(Date + " " + Hour);
        assertEquals(dt, localDateTime);

        LocalDateTime dr = LocalDateTime.parse(Date1 + " " + Hour1, formatter);
        LocalDateTime localDateTime1 = positionController.dateTime(Date1 + " " + Hour1);
        assertEquals(dr, localDateTime1);

        Ship ship = App.getInstance().getCompany().getShipStore().getShip("211331640");

        LocalDateTime dt1 = LocalDateTime.parse("31/12/2020 16:15", formatter);
        Message mes1 = new Message(dt1, 33.95038, -119.08169, 11.4, 119, 119, "B");

        LocalDateTime dt2 = LocalDateTime.parse("31/12/2020 18:03", formatter);
        Message mes2 = new Message(dt2, 33.78012, -118.72088, 11.6, 120, 121, "B");

        LocalDateTime dt3 = LocalDateTime.parse("31/12/2020 00:39", formatter);
        Message mes3 = new Message(dt3, 36.59348, -122.86674, 16.4, 147.6, 149, "B");

        LocalDateTime dt4 = LocalDateTime.parse("31/12/2020 01:25", formatter);
        Message mes4 = new Message(dt4, 36.39094, -122.71335, 19.7, 145.5, 147, "B");

        TreeSet<Message> messages = new TreeSet<>();
        messages.add(mes1);
        messages.add(mes2);
        messages.add(mes3);
        messages.add(mes4);

        TreeSet<Message> messageTreeSet = ship.getAllMessages();

        Assert.assertEquals(messages, messageTreeSet);
        Assert.assertNotEquals(0, messageTreeSet.size());

        List<String> actualList = positionController.PeriodPosition(Date, Hour,Date1, Hour1, "211331640");
        assertNotEquals(Collections.emptyList(), actualList);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format("%-20s %-20s %-20s %-20s %-20s",
                ship.getName(), Date, Hour, mes1.getLatitude(), mes1.getLongitude()));
        expectedList.add(String.format("%-20s %-20s %-20s %-20s %-20s",
                ship.getName(), Date1, Hour1, mes2.getLatitude(), mes2.getLongitude()));

        for (String s: actualList){
            assertTrue((s.contains(String.valueOf(mes1.getLatitude())) && s.contains(String.valueOf(mes1.getLongitude())))||(s.contains(String.valueOf(mes2.getLatitude())) && s.contains(String.valueOf(mes2.getLongitude()))));
        }

        assertEquals(expectedList, actualList);
    }

    @Test
    public void testPeriodPosition7(){
        String Date = "31/12/2020";
        String Hour = "16:20";
        String Date1 = "31/12/2020";
        String Hour1 = "18:04";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dt = LocalDateTime.parse(Date + " " + Hour, formatter);

        LocalDateTime localDateTime = positionController.dateTime(Date + " " + Hour);

        LocalDateTime dtr = LocalDateTime.parse(Date1 + " " + Hour1, formatter);

        LocalDateTime localDateTime1 = positionController.dateTime(Date1 + " " + Hour1);

        assertEquals(dt, localDateTime);
        assertEquals(dtr, localDateTime1);

        Ship ship = App.getInstance().getCompany().getShipStore().getShip("211331640");

        LocalDateTime dt1 = LocalDateTime.parse("31/12/2020 16:15", formatter);
        Message mes1 = new Message(dt1, 33.95038, -119.08169, 11.4, 119, 119, "B");

        LocalDateTime dt2 = LocalDateTime.parse("31/12/2020 18:03", formatter);
        Message mes2 = new Message(dt2, 33.78012, -118.72088, 11.6, 120, 121, "B");

        LocalDateTime dt3 = LocalDateTime.parse("31/12/2020 00:39", formatter);
        Message mes3 = new Message(dt3, 36.59348, -122.86674, 16.4, 147.6, 149, "B");

        LocalDateTime dt4 = LocalDateTime.parse("31/12/2020 01:25", formatter);
        Message mes4 = new Message(dt4, 36.39094, -122.71335, 19.7, 145.5, 147, "B");

        TreeSet<Message> messages = new TreeSet<>();
        messages.add(mes1);
        messages.add(mes2);
        messages.add(mes3);
        messages.add(mes4);

        TreeSet<Message> messageTreeSet = ship.getAllMessages();

        Assert.assertEquals(messages, messageTreeSet);
        Assert.assertEquals(4, messageTreeSet.size());

        List<String> actualList = positionController.DatePosition(Date, Hour, "211331640");
        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format("%-20s %-20s %-20s %-20s %-20s",
                ship.getName(), Date, Hour, mes1.getLatitude(), mes1.getLongitude()));
        expectedList.add(String.format("%-20s %-20s %-20s %-20s %-20s",
                ship.getName(), Date1, Hour1, mes2.getLatitude(), mes2.getLongitude()));

        assertTrue(actualList.size()==0);

        assertNotEquals(expectedList, actualList);
    }

    @Test
    public void testCheckDate() {
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("/"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 42"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" /"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("42 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("/ "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("  2020/03/01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("  42"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("  /"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/01 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/012020-03-01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/012020/03/01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/01:"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/01Date"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/0142"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 2020/03/01/"));
        assertThrows(IllegalArgumentException.class,
                () -> (new PositionController()).checkDate(" 2020/03/01lapr.project.model.Message"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 42 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 422020/03/01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 4242"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" 42/"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" / "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate(" //"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01  "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01 2020-03-01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01 2020/03/01"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01 :"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01 Date"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01 42"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01 /"));
        assertThrows(IllegalArgumentException.class,
                () -> (new PositionController()).checkDate("2020/03/01 lapr.project.model.Message"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/012020-03-01 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/012020/03/01 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01: "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01Date "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/0142 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkDate("2020/03/01/ "));
        assertThrows(IllegalArgumentException.class,
                () -> (new PositionController()).checkDate("2020/03/01lapr.project.model.Message "));
    }

    @Test
    public void testCheckHour() {
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(""));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(":"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("42"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" :"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" 42"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(": "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("42 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("  :"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("  42"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" : "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" ::"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" 42 "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" 42:"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(" 4242"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(":  "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(": :"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour(":: "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("42  "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("42 :"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("42 42"));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("42: "));
        assertThrows(IllegalArgumentException.class, () -> (new PositionController()).checkHour("4242 "));
    }

    @Test
    public void testCheckPeriod() {
        PositionController positionController = new PositionController();
        LocalDateTime Date = LocalDateTime.of(1, Month.JANUARY, 1, 1, 1, 1);
        assertThrows(IllegalArgumentException.class,
                () -> positionController.checkPeriod(Date, LocalDateTime.of(1, 1, 1, 1, 1)));
    }

    @Test
    public void checkAllRulesCorrect() {
        System.out.println("Check if All the rules are correct");
        positionController.DatePosition("31/12/2020", "12:30", "211331640");
    }

    @Test
    public void checkDateRulesBlank() {
        System.out.println("Check if the Date is blank or if it has the format DD/MM/YY");

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date cannot be blank.");

        positionController.DatePosition("", "12:30", "211331640");
    }

    @Test
    public void checkDateRulesDayLess() {
        System.out.println("Check if the Date is blank or if it has the format DD/MM/YY");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date day is invalid (1-31)");

        positionController.DatePosition("0/12/2020", "12:30", "211331640");
    }

    @Test
    public void checkDateRulesDayMore() {
        System.out.println("Check if the Date is blank or if it has the format DD/MM/YY");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date day is invalid (1-31)");

        positionController.DatePosition("32/12/2020", "12:30", "211331640");
    }

    @Test
    public void checkDateRulesMonthLess() {
        System.out.println("Check if the Date is blank or if it has the format DD/MM/YY");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date month is invalid (1-12)");

        positionController.DatePosition("31/0/2020", "12:30", "211331640");
    }

    @Test
    public void checkDateRulesMonthMore() {
        System.out.println("Check if the Date is blank or if it has the format DD/MM/YY");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date month is invalid (1-12)");

        positionController.DatePosition("31/15/2020", "12:30", "211331640");
    }

    @Test
    public void checkDateRulesYearLess() {
        System.out.println("Check if the Date is blank or if it has the format DD/MM/YY");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date year is invalid (2000-2022)");

        positionController.DatePosition("31/12/1999", "12:30", "211331640");
    }

    @Test
    public void checkDateRulesYearMore() {
        System.out.println("Check if the Date is blank or if it has the format DD/MM/YY");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Date year is invalid (2000-2022)");

        positionController.DatePosition("31/12/2024", "12:30", "211331640");
    }

    //------------------------------------------------------------------------------------------

    @Test
    public void checkHourRulesBlank() {
        System.out.println("Check if the Hour is blank or if it has the format HH:mm");

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Hour cannot be blank.");

        positionController.DatePosition("31/12/2020", "", "211331640");
    }

    @Test
    public void checkHourRulesHourLess() {
        System.out.println("Check if the Hour is blank or if it has the format HH:mm");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Hour is invalid (00:00 - 23:59)");

        positionController.DatePosition("31/12/2020", "-02:30", "211331640");
    }

    @Test
    public void checkHourRulesHourMore() {
        System.out.println("Check if the Hour is blank or if it has the format HH:mm");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Hour is invalid (00:00 - 23:59)");

        positionController.DatePosition("31/12/2020", "29:30", "211331640");
    }

    @Test
    public void checkHourRulesMinutesLess() {
        System.out.println("Check if the Hour is blank or if it has the format HH:mm");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Minutes are invalid (00:00 - 23:59)");

        positionController.DatePosition("31/12/2020", "12:-01", "211331640");
    }

    @Test
    public void checkHourRulesMinutesMore() {
        System.out.println("Check if the Hour is blank or if it has the format HH:mm");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Minutes are invalid (00:00 - 23:59)");

        positionController.DatePosition("31/12/2020", "12:64", "211331640");
    }

    //------------------------------------------------------------------------------------------
    @Test
    public void checkShipCodeRules() {
        System.out.println("Check if the Ship Code respect the rules");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The code provided does not match to any ship in the database.");

        positionController.DatePosition("31/12/2020", "12:30", "");
    }
    //------------------------------------------------------------------------------------------

    @Test
    public void checkPeriodDatesRulesIncorrect() {
        System.out.println("Check if the Ship Code respect the rules");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The first date is after the second one");

        positionController.PeriodPosition("31/12/2020", "12:30", "30/12/2020", "12:32", "211331640");
    }

    @Test
    public void checkPeriodDatesRulesIncorrect1() {
        System.out.println("Check if the Ship Code respect the rules");
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The first date is after the second one");

        positionController.PeriodPosition("31/12/2020", "12:30", "31/12/2020", "12:29", "211331640");
    }

    @Test
    public void checkShipNull(){
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("This ship does not exist. :((");
        PositionController p = new PositionController();
        p.DatePosition("31/12/2020", "12:30", "000000000");

    }

    @Test
    public void checkdateTime(){
        String s = "12/31/1001";

        exceptionRule.expect(java.time.format.DateTimeParseException.class);
        exceptionRule.expectMessage("Text '"+s+"' could not be parsed at index 10");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        formatter.parse(s);

    }
}