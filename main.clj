(ns clojure.examples.aoc22day3
   (:gen-class))

(require '[clojure.java.io :as jio]
         '[clojure.set :as set])

(defn FirstHalf [line] 
    (let [len (count line)] 
        (subs line 0 (/ len 2))))

(defn SecondHalf [line] 
    (let [len (count line)] 
        (subs line (/ len 2))))

(defn packIntersection [line]
  (set/intersection (set (FirstHalf line)) (set (SecondHalf line))))

(defn charScore [c]     
    (println c)
    (let [intVal (int c)] 
        (if (<= intVal (int \Z))
            (+ (- intVal (int \A)) 27)
            (+ (- intVal (int \a)) 1))))

(defn packScore [line] 
    (let [intersectSet (packIntersection line)]
        (reduce + (map charScore intersectSet))
        ))

(defn ReadLines []
    (with-open [rdr (jio/reader "Example.txt")]
      (doseq [line (line-seq rdr)]
          (println (str ">" line "<"))
          (println (str "= " (packScore line)))
        )))

(defn CalcLines []
    (with-open [rdr (jio/reader "Input.txt")] (println (reduce + (map packScore (line-seq rdr))))))

(ReadLines)
(CalcLines)