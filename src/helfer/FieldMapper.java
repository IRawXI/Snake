/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helfer;

import basis.Schlange;

/**
 *
 * @author Rudi
 */
public class FieldMapper {
    Typ[][] inputArray;
    Typ[][] outputArray;
    int f; //Faktor
    
    public Typ[][] map(Typ[][] given, int factor, Schlange snake){
        //System.out.println("FieldMapper.map-Aufruf");
        f = factor;
        
        this.inputArray = given;
        this.outputArray = new Typ[f*inputArray.length+1][f*inputArray[0].length+1];
        
        for(int i = 0; i < inputArray.length; i++){
            for(int j = 0; j < inputArray[0].length; j++){
                
                for(int l = 0; l < f; l++){
                    switch(inputArray[i][j]){
                        case LEER: 
                            for(int k = 0; k < f; k++){
                                outputArray[l+f*i][k+f*j]= Typ.LEER;
                            }
                            break;
                        case SCHLANGENKOERPER: 
                            for(int k = 0; k < f; k++){
                                outputArray[l+f*i][k+f*j] = singleSquareMap(i, j, l, k, inputArray, Typ.SCHLANGENKOERPER, snake);
                            }
                            /*
                            for(int k = 0; k < f; k++){
                                if(((k+f*j+1)%f==1) || ((l+f*i+1)%f==1)){
                                    outputArray[l+f*i][k+f*j]= Typ.LEER;
                                }
                                else {
                                    outputArray[l+f*i][k+f*j]= Typ.SCHLANGENKOERPER;
                                }
                            }//TO BE DELETED
                            */
                            break;
                        case SCHLANGENKOPF: 
                            for(int k = 0; k < f; k++){
                                outputArray[l+f*i][k+f*j] = singleSquareMap(i, j, l, k, inputArray, Typ.SCHLANGENKOPF, snake);
                            }
                            /*for(int k = 0; k < f; k++){
                                if((k+f*j+1)%f==1 || ((l+f*i+1)%f==1)){
                                    outputArray[l+f*i][k+f*j]= Typ.LEER;
                                }
                                else {
                                    outputArray[l+f*i][k+f*j]= Typ.SCHLANGENKOPF;
                                }
                            }//TO BE DELETED
                            */
                            break;
                        case ITEM_K: 
                            for(int k = 0; k < f; k++){
                                if((k+f*j+1)%f==1 || ((l+f*i+1)%f==1)){
                                    outputArray[l+f*i][k+f*j]= Typ.LEER;
                                }
                                else {
                                    outputArray[l+f*i][k+f*j]= Typ.ITEM_K;
                                }
                            }
                            break; 
                        case ITEM_L: 
                            for(int k = 0; k < f; k++){
                                if((k+f*j+1)%f==1 || ((l+f*i+1)%f==1)){
                                    outputArray[l+f*i][k+f*j]= Typ.LEER;
                                }
                                else {
                                    outputArray[l+f*i][k+f*j]= Typ.ITEM_L;
                                }
                            }
                            break;
                        case ITEM_S: 
                            for(int k = 0; k < f; k++){
                                if((k+f*j+1)%f==1 || ((l+f*i+1)%f==1)){
                                    outputArray[l+f*i][k+f*j]= Typ.LEER;
                                }
                                else {
                                    outputArray[l+f*i][k+f*j]= Typ.ITEM_S;
                                }
                            }
                            break;  
                        default:
                            break;      
                    }
                }
            }
        }
        for(int a = 0; a < outputArray.length; a++){
            outputArray[a][outputArray[0].length-1] = Typ.LEER;
        }
        for(int b = 0; b < outputArray[0].length; b++){
            outputArray[outputArray.length-1][b] = Typ.LEER;
        }
        
        return outputArray;
    }
    
    public Typ singleSquareMap(int i, int j, int l, int k, Typ[][] inputArray, Typ input, Schlange snake){ //only for SCHLANGENKOERPER and SCHLANGENKOPF
        Typ output = null;
        boolean left = false;
        boolean top = false;
        
        /*for(int pi1 = 0; pi1 < inputArray.length; pi1++){
            for(int pi2 = 0; pi2 < inputArray[0].length; pi2++){
                System.out.print(inputArray[pi1][pi2]+" ("+pi1+"|"+pi2+")  ");
            
            }
            System.out.println();
        }*/
        
        if(j!=0){
            if(inputArray[i][j-1]== Typ.SCHLANGENKOERPER || inputArray[i][j-1]== Typ.SCHLANGENKOPF){
                left = checkLeftNextOrPrev(snake, new Position(i, j), new Position(i-1, j));
                //System.out.println("left: "+left);
            }
            else{
                left = false;
            }
        }
        
        if(i!=0){
            if(inputArray[i-1][j]== Typ.SCHLANGENKOERPER || inputArray[i-1][j]== Typ.SCHLANGENKOPF){
                top = checkTopNextOrPrev(snake, new Position(i, j), new Position(i, j-1));
                //System.out.println("top_: "+top);
            }
            else {
                top = false;
            }
        } 
        
        //System.out.println("TODO here!!! File: FieldMapper.jaja Line: 126 -> specify as next or previous Snake-body-part :)");
        //if(((k+f*j)%f==0) || ((l+f*i)%f==0)){
            
            if(left){
                //System.out.println("TODO above!!! File: FieldMapper.jaja Line: 117 -> specify as next or previous Snake-body-part :)");
                if(top){
                    if(l==0){
                        if(k==0 || k==1 || k==f-1){
                            output = Typ.LEER;
                        }
                        else{
                            output = Typ.SCHLANGENKOERPER;
                        }
                    }
                    else{
                        if(k==0){
                            if(l==1 || l==f-1){
                                output = Typ.LEER;
                            }
                            else{
                                output = Typ.SCHLANGENKOERPER;
                            }
                        }
                        else {
                            output = input;
                        }
                    }
                }
                else {
                    if(l==0){
                        if(k==0 || k==1 || k==f-1){
                            output = Typ.LEER;
                        }
                        else{
                            output = Typ.SCHLANGENKOERPER;
                        }
                    }
                    else{
                        if(k==0){
                            output = Typ.LEER;
                        }
                        else {
                            output = input;
                        }
                    }
                }
                /*if(k==1 || k==f-1){
                    output = Typ.LEER;
                }
                else {
                    output = Typ.SCHLANGENKOERPER;
                }*/
            }
            else {
                if(top){
                    if(l==0){
                        output = Typ.LEER;
                    }
                    else{
                        if(k==0){
                            if(l==1 || l==f-1){
                                output = Typ.LEER;
                            }
                            else{
                                output = Typ.SCHLANGENKOERPER;
                            }
                        }
                        else {
                            output = input;
                        }
                    }
                    
                    /*if(l==1 || l==f-1){
                        output = Typ.LEER;
                    }
                    else {
                        if(l==0 || k==0){
                            output = Typ.LEER;
                        }
                        else{
                        output = Typ.SCHLANGENKOERPER;
                        }
                    }*/
                }
                else{
                    if(l==0){
                        output = Typ.LEER;
                    }
                    else {
                        if(k==0){
                            output = Typ.LEER;
                        }
                        else {
                            output = input;
                        }
                    }
                }
            }
            
        //}
        //else {
        //    output = input;
        //}
        /*
            if(inputArray[i-1][j]== Typ.SCHLANGENKOERPER || inputArray[i-1][j]== Typ.SCHLANGENKOPF){
                if(l==0){
                    if(k==0 || k==1 || k==(f-1)){
                        output = Typ.LEER;
                    }
                    else {
                        output = Typ.SCHLANGENKOERPER;
                    }
                    
                }
                else {
                    if(k==0){
                        if(l==0 || l==1 || l==(f-1)){
                            output = Typ.LEER;
                        }
                        else {
                            output = Typ.SCHLANGENKOERPER;
                        }
                    }
                }
            }
            if(inputArray[i][j-1]== Typ.SCHLANGENKOERPER || inputArray[i][j-1]== Typ.SCHLANGENKOPF){
                if(l==0){
                    if(k==0 || k==1 || k==(f-1)){
                        output = Typ.LEER;
                    }
                    else {
                        output = Typ.SCHLANGENKOERPER;
                    }
                    
                }
                else {
                    if(k==0){
                        if(l==0 || l==1 || l==(f-1)){
                            output = Typ.LEER;
                        }
                        else {
                            output = Typ.SCHLANGENKOERPER;
                        }
                    }
                }
            }
            else{
                output = input;
            }*/
        
        return output;
    }
    
    public boolean checkIfLeftNextOrPrev(Schlange snake, Position pos, Position leftKoord){ //checks if the left Field of input[][] ist Next or previous in snake
        //System.out.println("check if Next  //left");
        boolean leftIsNeighbor = false;
        //System.out.println(snake.schlangeContains(leftKoord)+"  //left");
        if(snake.schlangeContains(leftKoord)){
            int index = snake.getlastIndexOf(pos);
            //System.out.println(index+"  //left");
            if(index>1){
                //System.out.println("index > 1  //left");
                if((snake.getSnakePos(index-1).getPosX() == leftKoord.getPosX()) && (snake.getSnakePos(index-1).getPosY() == leftKoord.getPosY())){
                    leftIsNeighbor = true;
                }
            }
            else{}
            if(index-1<snake.getLaenge()){
                //System.out.println("index < snakelänge - 1  //left");
                if( (snake.getSnakePos(index+1).getPosX() == leftKoord.getPosX()) && (snake.getSnakePos(index+1).getPosY() == leftKoord.getPosY()) ){
                    leftIsNeighbor = true;
                }
            }
            else{}
        }
        return leftIsNeighbor;
    }
    
    public boolean checkIfTopNextOrPrev(Schlange snake, Position pos, Position topKoord){
        //System.out.println("check if Next  //top");
        boolean topIsNeighbor = false;
        //System.out.println(snake.schlangeContains(topKoord)+"  //top");
        if(snake.schlangeContains(topKoord)){
            int index = snake.getlastIndexOf(pos);
            //System.out.println(index+"  //top");
            if(index>1){
                //System.out.println("index > 1  //top");
                if((snake.getSnakePos(index-1).getPosX() == topKoord.getPosX()) && (snake.getSnakePos(index-1).getPosY() == topKoord.getPosY())){
                    topIsNeighbor = true;
                }
            }
            else{}
            if(index-1<snake.getLaenge()){
                //System.out.println("index < snakelänge - 1  //top");
                if( (snake.getSnakePos(index+1).getPosX() == topKoord.getPosX()) && (snake.getSnakePos(index+1).getPosY() == topKoord.getPosY()) ){
                    topIsNeighbor = true;
                }
            }
            else{}
        }
        return topIsNeighbor;
    }
    
    public boolean checkTopNextOrPrev(Schlange snake, Position pos, Position topKoord){
        boolean topIsNeighborInSnake = false;
        int snakeindex = -1;
        for(int a = 0; a < snake.getLaenge(); a++){
            //System.out.println("Snakeloop-if(Top) runs: a = "+a);
            //System.out.println("Snake: ("+snake.getSnakePos(a).getPosX()+"|"+snake.getSnakePos(a).getPosY()+")  Pos: ("+pos.getPosX()+"|"+pos.getPosY()+")");
            if(snake.getSnakePos(a).getPosX() == pos.getPosX() && snake.getSnakePos(a).getPosY() == pos.getPosY()){
                snakeindex = a;
                //System.out.println("Snakeloop-if(Top) successful: a = "+a);
            }
        }
        
        if(snakeindex > 0){
            if(snake.getSnakePos(snakeindex-1) == topKoord){
                topIsNeighborInSnake = true;
            }
            else{
                if(snakeindex < snake.getLaenge()-1){
                    if(snake.getSnakePos(snakeindex+1) == topKoord){
                        topIsNeighborInSnake = true;
                    }
                }
            }
        }
        
        /*
        
        System.out.println(snake.schlangeContains(topKoord)+"  //top NEW");
        if(snake.schlangeContains(topKoord)){
            int index = snake.getlastIndexOf(pos);
            System.out.println(index+"  //top NEW");
            if(index>1){
                System.out.println("index > 1  //top NEW");
                if((index-1) == snake.getlastIndexOf(topKoord)){
                    topIsNeighborInSnake = true;
                }
            }
            else{}
            if(index-1<snake.getLaenge()){
                System.out.println("index < snakelänge - 1  //top NEW");
                if( (index+1) == snake.getlastIndexOf(topKoord) ){
                    topIsNeighborInSnake = true;
                }
            }
            else{}
        }
        */
        return topIsNeighborInSnake;
    }
    
    public boolean checkLeftNextOrPrev(Schlange snake, Position pos, Position topKoord){
        boolean leftIsNeighborInSnake = false;
        int snakeindex = -1;
        for(int a = 0; a < snake.getLaenge(); a++){
            //System.out.println("Snakeloop-if(Top) runs: a = "+a);
            //System.out.println("Snake: ("+snake.getSnakePos(a).getPosX()+"|"+snake.getSnakePos(a).getPosY()+")  Pos: ("+pos.getPosX()+"|"+pos.getPosY()+")");
            if(snake.getSnakePos(a).getPosX() == pos.getPosX() && snake.getSnakePos(a).getPosY() == pos.getPosY()){
                snakeindex = a;
                //System.out.println("Snakeloop-if(Top) successful: a = "+a);
            }
        }
        
        if(snakeindex > 0){
            if(snake.getSnakePos(snakeindex-1) == topKoord){
                leftIsNeighborInSnake = true;
            }
            else{
                if(snakeindex < snake.getLaenge()-1){
                    if(snake.getSnakePos(snakeindex+1) == topKoord){
                        leftIsNeighborInSnake = true;
                    }
                }
            }
        }
        
        /*
        
        System.out.println(snake.schlangeContains(topKoord)+"  //top NEW");
        if(snake.schlangeContains(topKoord)){
            int index = snake.getlastIndexOf(pos);
            System.out.println(index+"  //top NEW");
            if(index>1){
                System.out.println("index > 1  //top NEW");
                if((index-1) == snake.getlastIndexOf(topKoord)){
                    topIsNeighborInSnake = true;
                }
            }
            else{}
            if(index-1<snake.getLaenge()){
                System.out.println("index < snakelänge - 1  //top NEW");
                if( (index+1) == snake.getlastIndexOf(topKoord) ){
                    topIsNeighborInSnake = true;
                }
            }
            else{}
        }
        */
        return leftIsNeighborInSnake;
    }
}
