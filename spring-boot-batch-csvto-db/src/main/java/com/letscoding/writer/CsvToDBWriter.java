package com.letscoding.writer;

import com.letscoding.entity.User;
import com.letscoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvToDBWriter implements ItemWriter<User> {

    private final UserRepository userRepository;

    @Override
    public void write(List<? extends User> list) throws Exception {

        System.out.println("Data Saved for Users: " + list);
        userRepository.saveAll(list);
    }
}
