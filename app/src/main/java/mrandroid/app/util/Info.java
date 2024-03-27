package mrandroid.app.util;

import java.util.ArrayList;
import mrandroid.app.model.FacultyModel;

public class Info {

    public static ArrayList<FacultyModel> getFaculties() {
        ArrayList<FacultyModel> hospitals = new ArrayList<>();
        hospitals.add(new FacultyModel("مستشفى ميت غمر العام", 30.75972703415985, 31.259824075744465));
        hospitals.add(new FacultyModel("مستشفي دماص المركزي", 30.849184476409633, 31.326313082832776));
        hospitals.add(new FacultyModel("مستشفي السنبلاوين العام", 30.931678803483123, 31.469135339208435));
        hospitals.add(new FacultyModel("مستشفى الدلتا", 31.09175010105062, 31.364765228780072));
        hospitals.add(new FacultyModel("مستشفى نبروه المركزي", 31.152882725713646, 31.30983359171251));
        hospitals.add(new FacultyModel("مستشفى بلقاس المركزي", 31.26798706808982, 31.359272065073316));
        hospitals.add(new FacultyModel("مستشفى الخير", 31.095696314137435, 31.419397114651854));
        return hospitals;
    }

}
