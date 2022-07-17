package com.redhat.user;

import com.redhat.user.profile.Student;
import com.redhat.user.profile.Tutor;
import com.redhat.user.profile.Srch;
import com.redhat.user.profile.Srch;
//import com.redhat.user.service.StudentRepository;
//import com.redhat.user.service.TutorRepository;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.redhat.user.app.DGService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class ProfileApplication {

//    @Autowired
//    private StudentRepository studentRepo;
//    @Autowired
//    private TutorRepository tutorRepo;

    @Autowired
    private DGService dgService;

    private RemoteCacheManager cacheManager;
    // Define a named cache with default JCache configuration
    //Map<String, Student> studentCache = cacheManager.getCache("student");  //dgService.getStudents();

    //Map<String,Tutor> tutorCache = cacheManager.getCache("tutor");  // dgService.getTutors();


    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }

//    @Bean
//    CommandLineRunner preLoadMongo() throws Exception {
//        return args -> {
//            // Drop the collection if it exists and then add default content
//            studentRepo.deleteAll();
//            tutorRepo.deleteAll();
//            studentRepo.insert(DEFAULT_STUDENT_LIST);
//            tutorRepo.insert(DEFAULT_TUTOR_LIST);
//
//        };
//
//
//    }

    @Bean
    CommandLineRunner preLoadCache() throws Exception {

        return args -> {

            for (Student s : DEFAULT_STUDENT_LIST){
                //String ui =
                dgService.getStudents().put(s.getUserId(),s);
            }

            for (Tutor t : DEFAULT_TUTOR_LIST){
                //String ui =
                dgService.getTutors().put(t.getUserId(),t);
            }

        };
    }



    private static List<Student> DEFAULT_STUDENT_LIST = new ArrayList<>();
    static {

        DEFAULT_STUDENT_LIST.add(new Student("1","Daniel","Medina","1234567","dmedina@email.com","first student in list", "boston university","computer science, math"));
    }

    private static List<Tutor> DEFAULT_TUTOR_LIST = new ArrayList<>();
    static {

        DEFAULT_TUTOR_LIST.add(new Tutor("2","Daniel","Med","2345678","dmedina@email.com","first student in list", "boston university","computer science, math, history"));
    }

//    private static List<Srch> DEFAULT_SEARCH_LIST = new ArrayList<>();
//    static{
//        DEFAULT_SEARCH_LIST.add(new Srch("daniel"));
//    }


}