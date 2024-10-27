package tictact;

import java.util.*;
import java.io.*;

//position character poschar
//position pos
public enum Pos{
        X('X'),
        O('O'),
        BLANK(' ');

        private final char pos;

        Pos(char posChar){
            this.pos = posChar;
        }

        public boolean checkBlank(){
            return this != BLANK;
        }

        public char getChar(){
            return this.pos;
        }
        @Override
        public String toString(){
            return String.valueOf(pos);
        }
    }

