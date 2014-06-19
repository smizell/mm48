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
