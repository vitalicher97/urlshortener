package urlshortener.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

@Service
public class IDConverterServiceImpl implements IDConverterService {

    private HashMap<Integer, Character> indexToCharMap = new HashMap<>();

    // To generate map values for conversion from base10 to base62
    // Uses numbers 0 - 9, characters a-z, A-Z
    @EventListener(ApplicationReadyEvent.class)
    private void keyValueGen(){
        char asciiIndex = 48;
        for(int i = 0; i <= 61; i++){
            indexToCharMap.put(i, asciiIndex);
            asciiIndex++;
            if(asciiIndex == 58){
                asciiIndex = 65;
            } else if(asciiIndex == 91){
                asciiIndex = 97;
            }
        }
    }

    public String convertRequestNum(int requestNum){
        Deque<Integer> remainderList = new LinkedList<>();
        int mapSize = indexToCharMap.size();
        int remainder;
        int integerPart;
        int quotient = requestNum;
        String convertedID = "";
        do{
            remainder = quotient % mapSize;
            integerPart = quotient / mapSize;

            remainderList.add(remainder);
            quotient = integerPart;
        }while(integerPart != 0);

        Iterator iteratorVals = remainderList.descendingIterator();

        while(iteratorVals.hasNext()){
            convertedID = convertedID + indexToCharMap.get(iteratorVals.next());
        }

        return convertedID;
    }

}
