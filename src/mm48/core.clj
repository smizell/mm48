(ns mm48.core)

(defn remove-zeros [row]
  (filter (fn [cell] (not= cell 0)) row))

(defn shift-row-left [row]
  (let [no-zeros (remove-zeros row)]
    (concat no-zeros (repeat (- 4 (count no-zeros)) 0))))

(defn shift-board-left [board]
  (map shift-row-left board))

(defn reduce-row-left [row]
  (let [cell0 (nth row 0)
        cell1 (nth row 1)
        cell2 (nth row 2)
        cell3 (nth row 3)]
    (cond
     (and (= cell0 cell1) (= cell2 cell3))
       (concat [(+ cell0 cell1) (+ cell2 cell3) 0 0])
     (= cell0 cell1)
       (concat [(+ cell0 cell1) cell2 cell3 0])
     (= cell1 cell2)
       (concat [cell0 (+ cell1 cell2) cell3 0])
     (= cell2 cell3)
       (concat [cell0 cell1 (+ cell2 cell3) 0])
     :else
       row)))

(defn reduce-board-left [board]
  (map reduce-row-left (shift-board-left board)))

(defn rotate-row-right [board row]
  [(nth (nth board 3) row)
   (nth (nth board 2) row)
   (nth (nth board 1) row)
   (nth (nth board 0) row)])

(defn rotate-board-right [board]
  (map (fn [row-num]
         (rotate-row-right board row-num)) (range 0 4)))

(defn rotate-board [rotations board]
  (if (= rotations 0)
    board
    (rotate-board (dec rotations) (rotate-board-right board))))

(defn move-board [first-rotate second-rotate board]
  (rotate-board second-rotate (reduce-board-left (rotate-board first-rotate board))))

(defn move-left [board]
  (reduce-board-left board))

(defn move-up [board]
  (move-board 3 1 board))

(defn move-right [board]
  (move-board 2 2 board))

(defn move-down [board]
  (move-board 1 3 board))
