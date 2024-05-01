package mrandroid.app.util;

import java.util.ArrayList;
import mrandroid.app.model.FacultyModel;

public class Info {

    public static ArrayList<FacultyModel> getFaculties() {
        ArrayList<FacultyModel> faculties = new ArrayList<>();
        faculties.add(new FacultyModel("الادارة العامة جامعة المنصورة", 31.044680803384153, 31.353582578627204));
        faculties.add(new FacultyModel("كلية التجارة جامعة المنصورة", 31.043128257881698, 31.355951672855984));
        faculties.add(new FacultyModel("كلية العلوم جامعة المنصورة", 31.04238620128542, 31.353624038400703));
        faculties.add(new FacultyModel("شئون طلاب كلية الزراعة جامعة المنصورة", 31.041857228661797, 31.355937992762737));
        faculties.add(new FacultyModel("كلية الهندسة جامعة المنصورة", 31.04297274850016, 31.35774239047645));
        faculties.add(new FacultyModel("حديقة البارون (جامعة المنصورة)", 31.043204640379063, 31.356641726121623));
        faculties.add(new FacultyModel("كلية الطب البيطري - جامعة المنصورة", 31.04269447753372, 31.359736217105528));
        faculties.add(new FacultyModel("كلية طب الأسنان جامعة المنصورة", 31.041759171802532, 31.36024144009696));
        faculties.add(new FacultyModel("مستشفي الاطفال الجامعي", 31.044441387566266, 31.359664042486695));
        faculties.add(new FacultyModel("كلية الطب - جامعة المنصورة", 31.044317713574618, 31.361143624078426));
        faculties.add(new FacultyModel("كلية التربية جامعة المنصورة", 31.04148862711463, 31.36246081274243));
        faculties.add(new FacultyModel("كلية التمريض - جامعة المنصورة", 31.03952522258861, 31.36039481140316));
        faculties.add(new FacultyModel("كلية الآداب جامعة المنصورة", 31.040375520858767, 31.35653346412021));
        faculties.add(new FacultyModel("كلية الفنون الجميلة جامعة المنصورة", 31.040074052352175, 31.354278004218283));
        faculties.add(new FacultyModel("كرياتيفا المنصورة", 31.040908885891888, 31.35423289502341));
        faculties.add(new FacultyModel("كلية الحاسبات والمعلومات", 31.042184311894953, 31.35152634302646));
        faculties.add(new FacultyModel("مسجد كلية الحاسبات والمعلومات", 31.04294955930358, 31.35198645684001));
        faculties.add(new FacultyModel("كلية الحقوق - جامعة المنصورة", 31.045109997334194, 31.35584780421593));
        return faculties;
    }

}
