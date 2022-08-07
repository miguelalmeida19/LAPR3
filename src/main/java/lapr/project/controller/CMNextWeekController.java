package lapr.project.controller;

import lapr.project.data.CMNextWeek;
import lapr.project.data.ImportFromDataBase;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CMNextWeekController {

    private final CMNextWeek c;
    private int working;

    //[US407] As Port manager, I intend to generate, a week in advance, the loading and
    //unloading map based on ships and trucks load manifests and corresponding travel plans,
    //to anticipate the level of sufficient and necessary resources (loading and unloading staff,
    //warehouse staff, ...).
    //      o Acceptance Criteria [BDDAD]:
    //              Week in advance is properly identified.
    //              Loading and unloading map is comprehensive.
    //              Loading and unloading map is clear with respect to the sufficient and
    //              necessary resources for loading and unloading tasks.

    public CMNextWeekController(){
        c = new CMNextWeek();
    }

    public int getID(){
        working = Integer.parseInt(ImportFromDataBase.getPortOfUser(App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString()));
        return working;
    }

    public String nextSunday() {
        return String.valueOf(LocalDate.now().plus((7 - LocalDate.now().getDayOfWeek().getValue()), ChronoUnit.DAYS));
    }

    public String nextSaturday() {
        return String.valueOf(LocalDate.now().plus((13 - LocalDate.now().getDayOfWeek().getValue()), ChronoUnit.DAYS));
    }

    public List<String> getMapLoading(){
        getID();
        return c.getMapLoading(working, nextSunday(), nextSaturday());
    }

    public List<String> getWarehouses(){
        return c.getWarehouses(working);
    }


    public List<String> getMapUnloading(){
        getID();
        List<String> warehouse = getWarehouses();

        String w = warehouse.get(0);
        String w2 = warehouse.get(1);

        return c.getMapUnloading(w, w2, nextSunday(), nextSaturday());
    }
}
