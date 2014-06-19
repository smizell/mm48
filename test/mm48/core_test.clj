(ns mm48.core-test
  (:require [expectations :refer :all]
            [mm48.core :refer :all]))

;; Should move all the numbers left and add when numbers can be added
(expect (reduce-board-left
         [[2 2 4 2]
          [2 4 0 4]
          [4 2 4 4]
          [4 4 4 4]])
        [[4 4 2 0]
         [2 8 0 0]
         [4 2 8 0]
         [8 8 0 0]])

;; Shift numbers left for an entire board if there are zeros
(expect (shift-board-left
         [[4 2 0 0]
          [4 0 0 4]
          [0 0 4 0]
          [2 2 2 0]])
        [[4 2 0 0]
         [4 4 0 0]
         [4 0 0 0]
         [2 2 2 0]])

;; Remove zeros from vector
(expect (remove-zeros [4 0 0 4]) [4 4])

;; Shift numbers left for a row if there are zeros
(expect (shift-row-left [4 0 0 4]) [4 4 0 0])

;; Adds up a row to the left where first and second are equal
;; and third and fourth are equal
(expect (reduce-row-left [2 2 4 4]) [4 8 0 0])

;; Adds up row to left where only first and second are equal
(expect (reduce-row-left [2 2 4 2]) [4 4 2 0])

;; Adds up row to left where only second and third are equal
(expect (reduce-row-left [2 4 4 2]) [2 8 2 0])

;; Adds up row to left where only third and fourth are equal
(expect (reduce-row-left [4 2 4 4]) [4 2 8 0])

;; Returns row if nothing is equal
(expect (reduce-row-left [4 2 4 2]) [4 2 4 2])

(def test-board [[4 2 0 0]
                 [4 0 0 4]
                 [0 0 4 0]
                 [2 2 2 0]])

(expect (rotate-board-right test-board)
        [[2 0 4 4]
         [2 0 0 2]
         [2 4 0 0]
         [0 0 4 0]])

(expect (rotate-row-right test-board 0) [2 0 4 4])
(expect (rotate-row-right test-board 1) [2 0 0 2])

(expect (rotate-board 3 test-board)
        [[0 4 0 0]
         [0 0 4 2]
         [2 0 0 2]
         [4 4 0 2]])

(expect (move-left test-board)
        [[4 2 0 0]
         [8 0 0 0]
         [4 0 0 0]
         [4 2 0 0]])

(expect (move-up test-board)
        [[8 4 4 4]
         [2 0 2 0]
         [0 0 0 0]
         [0 0 0 0]])

(expect (move-right test-board)
        [[0 0 4 2]
         [0 0 0 8]
         [0 0 0 4]
         [0 0 2 4]])

(expect (move-down test-board)
        [[0 0 0 0]
         [0 0 0 0]
         [8 0 4 0]
         [2 4 2 4]])



