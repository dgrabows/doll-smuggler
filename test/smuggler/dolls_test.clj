(ns smuggler.dolls-test
  (:require [clojure.string :as str])
  (:use clojure.test
        smuggler.dolls))

(defn load-dolls [s]
  (set
    (for [row (map #(str/split % #"\s+") (str/split-lines s))]
      {:name (first row)
       :weight (read-string (second row))
       :value (read-string (nth row 2))})))

(def w-1 400)

(def dolls-raw-1
  "luke       9   150
anthony    13    35
candice   153   200
dorothy    50   160
puppy      15    60
thomas     68    45
randal     27    60
april      39    40
nancy      23    30
bonnie     52    10
marc       11    70
kate       32    30
tbone      24    15
tranny     48    10
uma        73    40
grumpkin   42    70
dusty      43    75
grumpy     22    80
eddie       7    20
tory       18    12
sally       4    50
babe       30    10")

(def dolls-1 (load-dolls dolls-raw-1))

(def solution-raw-1
  "sally       4    50
eddie       7    20
grumpy     22    80
dusty      43    75
grumpkin   42    70
marc       11    70
randal     27    60
puppy      15    60
dorothy    50   160
candice   153   200
anthony    13    35
luke        9   150")

(def solution-1 (load-dolls solution-raw-1))

(deftest case-1
  (testing "Example one finds correct optimal dolls."
    (is (= solution-1 (pack-bag w-1 dolls-1)))))