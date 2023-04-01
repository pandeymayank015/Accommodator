package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImplementation implements FavoriteService {
    @Autowired
    com.project.Accommodator.repository.FavoriteRepository FavoriteRepository;

    public FavoriteServiceImplementation() {
        super();
    }

    @Override
    public Favorite createFavorite(Favorite Favorite) {

        return FavoriteRepository.save(Favorite);
    }

    @Override
    public Iterable<Posting> getFavoriteById(int id) {
        Iterable<Posting> posts = FavoriteRepository.findFavoritesById(id);
        return posts;
    }
}
//package com.project.Accommodator.service.implementation;
//
//import com.project.Accommodator.model.Favorite;
//import com.project.Accommodator.model.Posting;
//import com.project.Accommodator.model.Student;
//import com.project.Accommodator.repository.StudentRepository;
//import com.project.Accommodator.repository.PostingRepository;
//import com.project.Accommodator.service.FavoriteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FavoriteServiceImplementation implements FavoriteService {
//    @Autowired
//    com.project.Accommodator.repository.FavoriteRepository FavoriteRepository;
//    @Autowired
//    StudentRepository studentRepository;
//    @Autowired
//    PostingRepository postingRepository;
//
//    public FavoriteServiceImplementation() {
//        super();
//    }
//
//    @Override
//    public Favorite createFavorite(int studentId, int postId) {
//        Student student = studentRepository.findById(studentId).orElse(null);
//        Posting posting = postingRepository.findById(postId).orElse(null);
//
//        if (student != null && posting != null) {
//            Favorite favorite = new Favorite();
//            favorite.setStudent(student);
//            favorite.setPosting(posting);
//            return FavoriteRepository.save(favorite);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Iterable<Posting> getFavoriteById(int id) {
//        Iterable<Posting> posts = FavoriteRepository.findFavoritesById(id);
//        return posts;
//    }
//}
