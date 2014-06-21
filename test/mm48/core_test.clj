(ns mm48.core-test
  (:require [expectations :refer :all]
            [mm48.core :refer :all]))

;; Should move all the numbers left and add when numbers can be added
(expect (merge-board-left
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
(expect (merge-row-left [2 2 4 4]) [4 8 0 0])

;; Adds up row to left where only first and second are equal
(expect (merge-row-left [2 2 4 2]) [4 4 2 0])

;; Adds up row to left where only second and third are equal
(expect (merge-row-left [2 4 4 2]) [2 8 2 0])

;; Adds up row to left where only third and fourth are equal
(expect (merge-row-left [4 2 4 4]) [4 2 8 0])

;; Returns row if nothing is equal
(expect (merge-row-left [4 2 4 2]) [4 2 4 2])

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

(expect (merge-board-left test-board)
        [[4 2 0 0]
         [8 0 0 0]
         [4 0 0 0]
         [4 2 0 0]])

(expect (merge-board-up test-board)
        [[8 4 4 4]
         [2 0 2 0]
         [0 0 0 0]
         [0 0 0 0]])

(expect (merge-board-right test-board)
        [[0 0 4 2]
         [0 0 0 8]
         [0 0 0 4]
         [0 0 2 4]])

(expect (merge-board-down test-board)
        [[0 0 0 0]
         [0 0 0 0]
         [8 0 4 0]
         [2 4 2 4]])

;; Finds the index for zeros in a vector
(expect (indexes-for-zeros [2 0 4 0]) [1 3])
(expect (indexes-for-zeros [2 2 4 4]) [])

(expect (coordinates-for-zeros [[2 0 4 4]
                                [2 0 0 2]
                                [2 4 0 0]
                                [0 0 4 0]])
        [[0 1]
         [1 1] [1 2]
         [2 2] [2 3]
         [3 0] [3 1] [3 3]])

(expect (rand-empty-cell test-board) (in (coordinates-for-zeros test-board)))

(expect (rand-numb-to-insert) (in [2 4]))

(expect (coordinates-for-zeros (insert-rand-cell [[2 2 4 2]
                                                  [2 4 0 4]
                                                  [4 2 4 4]
                                                  [4 4 4 4]]))
        [])


(expect (count (coordinates-for-zeros (starting-board))) 14)

(expect (count (coordinates-for-zeros (move-left test-board))) 9)

(expect (count (coordinates-for-zeros (move-up test-board))) 9)

(expect (count (coordinates-for-zeros (move-right test-board))) 9)

(expect (count (coordinates-for-zeros (move-down test-board))) 9)

(expect (move? [[2 0 4 4]
                [2 0 0 2]
                [2 4 0 0]
                [0 0 4 0]])
        true)

(def no-moves [[2 4 6 8]
               [8 6 4 2]
               [2 4 6 8]
               [8 6 4 2]])

(expect (move? no-moves) false)

(expect (move-left? [[4 4 6 8]
                     [8 6 4 2]
                     [2 4 6 8]
                     [8 6 4 2]])
        true)

(expect (move-left? no-moves) false)

(expect (move-up? [[8 4 6 8]
                   [8 6 4 2]
                   [2 4 6 8]
                   [8 6 4 2]])
        true)

(expect (move-up? no-moves) false)

(expect (move-right? [[4 4 6 8]
                      [8 6 4 2]
                      [2 4 6 8]
                      [8 6 4 2]])
        true)

(expect (move-right? no-moves) false)

(expect (move-down? [[8 4 6 8]
                     [8 6 4 2]
                     [2 4 6 8]
                     [8 6 4 2]])
        true)

(expect (move-down? no-moves) false)
