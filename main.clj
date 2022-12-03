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

(defn PackIntersection [line]
  (set/intersection (set (FirstHalf line)) (set (SecondHalf line))))

(defn CharScore [c]     
    (println c)
    (let [intVal (int c)] 
        (if (<= intVal (int \Z))
            (+ (- intVal (int \A)) 27)
            (+ (- intVal (int \a)) 1))))

(defn PackScore [line] 
    (let [intersectSet (PackIntersection line)]
        (reduce + (map CharScore intersectSet))
        ))

(defn ReadLines []
    (with-open [rdr (jio/reader "Example.txt")]
      (doseq [line (line-seq rdr)]
          (println (str ">" line "<"))
          (println (str "= " (PackScore line)))
        )))

(defn CalcLines []
    (with-open [rdr (jio/reader "Input.txt")] 
        (println (reduce + (map PackScore (line-seq rdr))))))

(defn FindBadge 
    ([line1 line2 line3] 
         (println (set/intersection (set line1) (set line2) (set line3))))
    ([lines]
        (FindBadge (first lines) (first (next lines)) (first (next (next lines))) )))

;; (ReadLines)
;; (CalcLines)

(defn FindBadges []
    (with-open [rdr (jio/reader "Input.txt")] 
        (doseq [part (partition 3 (line-seq rdr))] 
            (FindBadge part) )))

(defn CalcBadges []
    (with-open [rdr (jio/reader "Input.txt")] 
        (doseq [part (partition 3 (line-seq rdr))] 
            (FindBadge part) )))

(defn CalcBadges2 []
    (with-open [rdr (jio/reader "Input.txt")] 
        (println (reduce + (map PackScore (FindBadge (partition 3 (line-seq rdr))))))))

(CalcBadges2)