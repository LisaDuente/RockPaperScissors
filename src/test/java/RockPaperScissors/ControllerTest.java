package RockPaperScissors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Model model;
    Controller control;
    User user;

    @BeforeEach
    void ModelAndGame(){
        model = new Model();
        control = new Controller(model);
        user = new User("Lisa", 2,3);
        model.setCurrentUserName("NeonPinky");
        model.setUser(user);
    }

    @Test
    void playGameNullString() {
        //input
        String s = null;

        //when
        control.playGame(s);

        //result
        assertEquals(model.getError(),s);
        assertEquals(model.getMessage(),s);
    }

    @Test
    void playGameWrongString() {
        //input
        String s = "papar";

        //when
        control.playGame(s);

        //result
        assertEquals(model.getError(),"Controller::setInput:error");
    }

    @Test
    void playGameUserNull() {
        //input
        model.setUser(null);

        //when
        control.playGame("scissors");

        //result
        assertEquals(model.getMessage(),"Please choose a user first!");
    }

    @Test
    void loadUserNotSaved() {
        //input
        String s = "whsjalm";

        //when
        control.loadUser(s);

        //result
        assertEquals("There is no user "+s,model.getError());
    }

    @Test
    void loadUserStringNull() {
        //input
        String s = null;

        //when
        control.loadUser(s);

        //result
        assertEquals("Please choose a user first!", model.getError());
    }

    @Test
    void savePlayerLongUserName() {
        //input
        String userName = "s";
        String actualName = "Bernd";
        int i = 0;
        while(i<101){
            userName = userName.concat("ss");
            i++;
        }

        //when
        control.savePlayer(userName,actualName);

        //result
        assertEquals("User name to long!", model.getError());
    }

    @Test
    void savePlayerLongActualName() {
        //input
        String userName = "Bernd";
        String actualName = "s";
        int i = 0;
        while(i<51){
            actualName = actualName.concat("ss");
            i++;
        }

        //when
        control.savePlayer(userName,actualName);

        //result
        assertEquals("User name to long!", model.getError());
    }

    @Test
    void savePlayerLongShortName() {
        //input
        String userName = "";
        String actualName = "s";

        //when
        control.savePlayer(userName,actualName);

        //result
        assertEquals("User name too short!", model.getError());
    }

    @Test
    void savePlayerLongShortName2() {
        //input
        String userName = "s";
        String actualName = "";

        //when
        control.savePlayer(userName,actualName);

        //result
        assertEquals("User name too short!", model.getError());
    }

    @Test
    void savePlayerAlreadyExisting() {
        //input
        String userName = "1";
        String actualName = "Alexandra";

        //when
        control.savePlayer(userName,actualName);

        //result
        assertEquals("User already exists!", model.getError());
    }

    @Test
    void updateHashmap() {
        //input
        TreeMap<String, String> testMap = new TreeMap<>();
        testMap.put("Blöd","Lisa,0,0");
        testMap.put("hallo","Knoten,3,4");
        model.getSaver().setMap(testMap);

        //when
        control.updateHashmap();

        //result
        assertEquals("Lisa,18,16",model.getSaver().getMap().get("1"));
        assertNull(model.getSaver().getMap().get("Blöd"));
    }

    @Test
    void resetGame() {
        //input
        model.getGame().setWinComputer(4);
        model.getGame().setWinUser(5);

        //when
        control.resetGame();

        //result
        assertEquals(0,model.getGame().getWinComputer());
        assertEquals(0,model.getGame().getWinUser());
    }

    @Test
    void getMap() {
        //input


        //when


        //result
    }

    @Test
    void getWinsUser() {
        //input


        //when


        //result
    }

    @Test
    void getWinsComputer() {
        //input


        //when


        //result
    }
}