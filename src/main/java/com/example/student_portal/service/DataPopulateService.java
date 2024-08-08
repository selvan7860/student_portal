package com.example.student_portal.service;


import com.example.student_portal.dao.Semester;
import com.example.student_portal.dao.Subject;
import com.example.student_portal.exception.CustomException;
import com.example.student_portal.repository.SemesterRepository;
import com.example.student_portal.repository.SubjectRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class DataPopulateService {

    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;

    public DataPopulateService(SemesterRepository semesterRepository, SubjectRepository subjectRepository) {
        this.semesterRepository = semesterRepository;
        this.subjectRepository = subjectRepository;
    }

    public Object addSemester(MultipartFile file) throws Exception{

        Reader csvReader = new InputStreamReader(file.getInputStream());
        CSVReader reader = null;
        Integer index = 0;
        try{
            reader = new CSVReaderBuilder(csvReader).build();
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null){
                index = index + 1;
                if(nextLine[0].trim() != null){
                    String semesterNo = nextLine[0].trim();
                    Semester semesters = semesterRepository.findBySemesterNo(semesterNo);
                    if(semesters != null){
                        throw  new CustomException("Semester Already Exists", HttpStatus.BAD_REQUEST);
                    }
                    Semester semester = new Semester();
                    semester.setSemesterNo(semesterNo);
                    semesterRepository.save(semester);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Semester Updated";
    }

    public Object addSubject(MultipartFile file) throws  Exception{
        Reader csvReader = new InputStreamReader(file.getInputStream());
        CSVReader reader = null;
        Integer index = 0;
        try {
            reader = new CSVReaderBuilder(csvReader).build();
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null){
                index = index + 1;
                if(nextLine[0].trim() != null){
                    String subjectCode = nextLine[0].trim();
                    String subjectName = nextLine[1].trim();
                    String semesterNo = nextLine[2].trim();

                    Semester semester = semesterRepository.findBySemesterNo(semesterNo);
                    if(semester == null){
                        throw new CustomException("There Is No Semester No", HttpStatus.BAD_REQUEST);
                    }
                    Subject subject = new Subject();
                    subject.setSubjectCode(subjectCode);
                    subject.setSubjectName(subjectName);
                    subject.setSemester(semester);
                    subjectRepository.save(subject);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Subject Updated";
    }
}
