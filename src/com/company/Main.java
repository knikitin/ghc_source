package com.company;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.time.LocalTime.now;

class TaskDTO {
    int r;
}

public class Main {

    private static void fillTaskHead(TaskDTO task, String head) {
        Integer[] numbers = Arrays.stream(head.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        task.r = numbers[0];
    }

    private static TaskDTO loadFile(String fileName) {
        TaskDTO task = new TaskDTO();

        try (Stream<String> stream = Files.lines(FileSystems.getDefault().getPath("inputdata", fileName))) {
            String[] fileContent = stream.toArray(String[]::new);
            fillTaskHead(task, fileContent[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return task;
    }


    private static void outputResults(String someObject, String fileName) {
        int score = 0;
        int number = 0;
        ArrayList<String> fileStrings = new ArrayList<>();

        fileStrings.add(someObject);

        System.out.println("score: " + score);
        // add in start
        fileStrings.add(0, String.valueOf(number));

        try {
            Files.write(Paths.get("result-"+fileName + ".res"), fileStrings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    static void workOnTask(String fileName){
        TaskDTO task = loadFile(fileName);
        System.out.println("check after loading " + now());
        System.out.println("check after fill " + now());
        outputResults("some object from ", fileName);
    }

    public static void main(String[] args) {
        workOnTask("big.in");
        workOnTask("example.in");
        workOnTask("medium.in");
        workOnTask("small.in");
        System.out.println("check start " + now());
    }
}
