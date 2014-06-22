(ns mm48.core)

(defn indexes-for-zeros [row]
  (->> row
       (map-indexed vector)
       (filter #(= (second %) 0))
       (map first)))

(defn remove-zeros [row]
  (filter #(not= 0 %) row))

(defn coordinates-for-zeros [board]
  (->> board
       (map indexes-for-zeros)
       (map-indexed vector)
       (filter #(not (empty? (second %))))
       (map (fn [[row cells]] (for [cell cells] [row cell])))
       (reduce concat)))

(defn shift-row-left [row]
  (let [no-zeros (remove-zeros row)]
    (concat no-zeros (repeat (- 4 (count no-zeros)) 0))))

(defn shift-board-left [board]
  (map shift-row-left board))

(defn merge-row-left [row]
  (let [cell0 (nth row 0)
        cell1 (nth row 1)
        cell2 (nth row 2)
        cell3 (nth row 3)]
    (cond
       (and (= cell0 cell1) (= cell2 cell3))
         [(+ cell0 cell1) (+ cell2 cell3) 0 0]
       (= cell0 cell1)
         [(+ cell0 cell1) cell2 cell3 0]
       (= cell1 cell2)
         [cell0 (+ cell1 cell2) cell3 0]
       (= cell2 cell3)
         [cell0 cell1 (+ cell2 cell3) 0]
       :else
         (vec row))))

(defn merge-board-left [board]
  (->> board
       (shift-board-left)
       (map merge-row-left)
       (vec)))

(defn rotate-row-right [board row]
  [(nth (nth board 3) row)
   (nth (nth board 2) row)
   (nth (nth board 1) row)
   (nth (nth board 0) row)])

(defn rotate-board-right [board]
  (vec (map (fn [row-num]
         (rotate-row-right board row-num)) (range 4))))

(defn rotate-board [rotations board]
  (if (= rotations 0)
    board
    (rotate-board (dec rotations) (rotate-board-right board))))

(defn merge-board [first-rotate second-rotate board]
  (rotate-board second-rotate (merge-board-left (rotate-board first-rotate board))))

(defn merge-board-up [board]
  (merge-board 3 1 board))

(defn merge-board-right [board]
  (merge-board 2 2 board))

(defn merge-board-down [board]
  (merge-board 1 3 board))

(defn rand-empty-cell [board]
  (->> (coordinates-for-zeros board)
       (rand-nth)))

(defn rand-numb-to-insert [] (rand-nth [2 2 2 4]))

(defn insert-rand-cell [board]
  (let [[row cell] (rand-empty-cell board)
        n (rand-numb-to-insert)]
    (assoc board row (assoc (nth board row) cell n))))

(defn starting-board []
  (->> [[0 0 0 0]
        [0 0 0 0]
        [0 0 0 0]
        [0 0 0 0]]
       (insert-rand-cell)
       (insert-rand-cell)))

(defn move-board [board direction]
  (->> board
       (direction)
       (insert-rand-cell)))

(defn move-left [board]
  (move-board board merge-board-left))

(defn move-up [board]
  (move-board board merge-board-up))

(defn move-right [board]
  (move-board board merge-board-right))

(defn move-down [board]
  (move-board board merge-board-down))

(defn move? [board]
  (or (not= (merge-board-left board) board)
      (not= (merge-board-up board) board)
      (not= (merge-board-right board) board)
      (not= (merge-board-down board) board)))

(defn move-left? [board]
  (not= (merge-board-left board) board))

(defn move-up? [board]
  (not= (merge-board-up board) board))

;; Even though we are testing right, this tests to
;; see if we can move left. It's less code, and if you
;; can move left, you can move right
(defn move-right? [board]
  (not= (merge-board-right board) board))

;; Reason for using move-up? is the same reason as comment
;; for move-right?
(defn move-down? [board]
  (not= (merge-board-down board) board))
