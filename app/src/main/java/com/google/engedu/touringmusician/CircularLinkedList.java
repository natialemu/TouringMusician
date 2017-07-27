/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.touringmusician;


import android.graphics.Point;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<Point> {

    private class Node {
        Point point;
        Node prev, next;
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
    }

    Node head;
    Node newlyInsertedNode;
    float total;

    public void insertBeginning(Point p) {
        Node newNode = new Node();
        newNode.point = p;
        newlyInsertedNode = newNode;

        if(head == null){//node is empty

            newNode.prev = newNode;
            newNode.next = newNode;
            head = newNode;

        }else{

            //new node's next needs to point to previous first
            //new node's prev needs to point to the last element
            //previous first's prev needs to point to new node
            //head needs to point to new no
            //last element
            newNode.next = head.next;//new node
            head.next = newNode;//head points to new node

            newNode.prev = newNode.next.prev;//newnode's prev points to the last element
            newNode.prev.next = newNode; // last element points to new node

            newNode.next.prev = newNode;// the previous first's prev element now points to new node


        }

    }

    private float distanceBetween(Point from, Point to) {
        return (float) Math.sqrt(Math.pow(from.y-to.y, 2) + Math.pow(from.x-to.x, 2));
    }

    public float totalDistance() {


        //have a node pointer that points to the newly inserted node
        if(head.next.equals(head.prev.prev)){
            total+=(distanceBetween(newlyInsertedNode.point,newlyInsertedNode.prev.point));
        }else{
            total+=(distanceBetween(newlyInsertedNode.point,newlyInsertedNode.prev.point) + distanceBetween(newlyInsertedNode.point, newlyInsertedNode.next.point));
        }


        /*Node currentNode = head.next;
        while(currentNode.next != head && currentNode != null)
        {
            total+= distanceBetween(currentNode.point, currentNode.next.point);
            currentNode = currentNode.next;

        }*/
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        return total;
    }

    public void insertNearest(Point p) {
        Node newNode = new Node();
        newNode.point = p;
        newlyInsertedNode = newNode;

        if(head == null){//node is empty

            newNode.prev = newNode;
            newNode.next = newNode;
            head = newNode;

        }else{

            //new node's next needs to point to previous first
            //new node's prev needs to point to the last element
            //previous first's prev needs to point to new node
            //head needs to point to new no
            //last element

            Node currentNode = head;

            float smallestDistance = distanceBetween(currentNode.point, newNode.point);
            Node closestNode = currentNode;
            currentNode = currentNode.next;
            while(currentNode != head){

                if(smallestDistance > distanceBetween(currentNode.point,newNode.point)){
                    smallestDistance = distanceBetween(currentNode.point,newNode.point);
                    closestNode = currentNode;
                }
                currentNode = currentNode.next;


            }

            newNode.next = closestNode.next;//new node points to closet node's next
            closestNode.next = newNode;//head points to new node

            newNode.prev = newNode.next.prev;//newnode's prev points to the last element
            newNode.prev.next = newNode; // last element points to new node

            newNode.next.prev = newNode;// the previous first's prev element now points to new node




            }



        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
    }

    public void insertSmallest(Point p) {
        /**
         **
         **  Goal is to go through the array and then find the insertion location that gives the least total distance
         *
         *    create new node with p
         *
         *   min total distance //total distance without new node + total distance if inserted right after first node
         *   start off from the second node:
         *   while the current node is not the first one:
         *       calculate what total distance would be if inserted right after the current node position by setting newlyInsertedNode.next to currentNode's next and prev to current node
         *
         **
         **/
        Node newNode = new Node();
        newNode.point = p;
        newlyInsertedNode = newNode;


        if(head == null){
            newNode.prev = newNode;
            newNode.next = newNode;
            head = newNode;

        }else{
            Node currentNode = head;
            newlyInsertedNode.next = currentNode.next;
            newlyInsertedNode.prev = currentNode;
            float minTotalDistance = totalDistance();
            Node minTotalDistanceNode = currentNode;

            currentNode = currentNode.next;
            while(currentNode != head){
                newlyInsertedNode.next = currentNode.next;
                newlyInsertedNode.prev =currentNode;
                if(totalDistance() < minTotalDistance){
                    minTotalDistance = totalDistance();
                    minTotalDistanceNode = currentNode;
                }
                currentNode = currentNode.next;
            }

            newNode.next = minTotalDistanceNode.next;//new node points to closet node's next
            minTotalDistanceNode.next = newNode;//head points to new node

            newNode.prev = newNode.next.prev;//newnode's prev points to the last element
            newNode.prev.next = newNode; // last element points to new node

            newNode.next.prev = newNode;// the previous first's prev element now points to new node
        }


    }

    public void reset() {
        head = null;
    }

    private class CircularLinkedListIterator implements Iterator<Point> {

        Node current;

        public CircularLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Point next() {
            Point toReturn = current.point;
            current = current.next;
            if (current == head) {
                current = null;
            }
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new CircularLinkedListIterator();
    }


}
