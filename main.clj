(ns clojure.examples.aoc22day3
   (:gen-class))

(require '[clojure.string :as str]
         '[clojure.java.io :as jio]
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
    (do 
        (println c)
        (let [intVal (int c)] 
            (if (<= intVal (int \z))
                (+ (- intVal (int \a)) 1)
                (+ (- intVal (int \A)) 1)))))

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

(ReadLines)