package dev.scheduler.classes;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultipleReceiverRequest {
    private  List<String> receivers;
    private  String copy;
    private  String hiddenCopy;
}
