(ns learnclojure)

(str "Hello" " " "World")

(+1 1)
(/ 5 2) ;= 5/2
(float (/ 5 2)) ; = 2.5
(quot 5 2) ; = 2 (quot m n) is the value of m/n, rounded towards 0 to the nearest integer.

(= 1 1)
(= 2 1)

(not true) ; false
(not (= "a" "b")) ; true

;nesting
(+ 1 (- 3 2)) ;2


(class 1) ; Integer literals are java.lang.Long by default
(class 1.); Float literals are java.lang.Double
(class ""); Strings always double-quoted, and are java.lang.String
(class false) ; Booleans are java.lang.Boolean
(class nil); The "null" value is called nil

'(+ 1 2) ; => (+ 1 2)
; (shorthand for (quote (+ 1 2)))

(eval '(+ 1 2)) ; => 3

; Lists are linked-list data structures, while Vectors are array-backed.
; Vectors and Lists are java classes too
(class [1 2 3]); => clojure.lang.PersistentVector
(class '(1 2 3)); => clojure.lang.PersistentList

(conj [1 2 3] 4) ;[1 2 3 4]
(def foo [1 2 3])
(conj foo 4) ; [1 2 3 4]
(concat foo [5 6]) ;(1 2 3 4 5 6)

(def bar(concat foo [4 5 6]))
(map inc bar) ;(2 3 4 5 6 7)

(filter even? foo); (2)
(filter even? bar);(2 4 6)
;--------
(reduce + [1 2 3 4])
; = (+ (+ (+ 1 2) 3) 4)
; => 10
;https://clojuredocs.org/clojure.core/reduce


;;functions

((fn [] "Hello World"))
(def every-first-program-ever (fn [] "Hello World"))
(every-first-program-ever) ;"Hello World"

(defn this-name [name](str "Hello " name))
(this-name "Steve") ; "Hello Steve"


; You can also use this shorthand to create functions:
(def hello2 #(str "Hello " %1))
(hello2 "Fanny") ; => "Hello Fanny"

; You can have multi-variadic functions, too
(defn hello3
  ([] "Hello World")
  ([name] (str "Hello " name)))
(hello3 "Jake") ; => "Hello Jake"
(hello3) ; => "Hello World"

; Functions can pack extra arguments up in a seq for you
(defn count-args [& args]
  (str "You passed " (count args) " args: " args))
(count-args 1 2 3 4 5 8) ; => "You passed 6 args: (1 2 3 4 5 8)"

(defn hello-count [name & args]
  (str "Hello " name ", you passed " (count args) " extra args"))
(hello-count "Finn" 1 2 3)
; => "Hello Finn, you passed 3 extra args"

;Maps
; Hash maps and array maps share an interface. Hash maps have faster lookups
; but don't retain key order.
(class {:a 1 :b 2 :c 3})
(class (hash-map :a 1 :b 2 :c 3))

(def keymap {:a 1, :b 2, :c 3})
keymap ;{:a 1, :b 2, :c 3}

(keymap :b) ;2

(def stringmap {"a" 1, "b" 2, "c" 3})
 (stringmap "a"); 1

 (stringmap "d") ; => nil


(def newkeymap (assoc keymap :d 4))
newkeymap ;{:a 1, :b 2, :c 3, :d 4}

(dissoc keymap :a :b)

newkeymap2; {:b 2, :c 3}

;Sets

(class #{1 2 3})
(set [1 2 3 1 2 3 3 2 1 3 2 1]) ; => #{1 2 3}
(conj #{1 2 3} 4) ;#{1 4 3 2}

(disj #{4 5 6} 5) ;{4 6}

; Test for existence by using the set as a function:
(#{1 2 3} 1) ; => 1
(#{1 2 3} 4) ; => nil

;forms
(if false "a" "b") ; => "b"
(if false "a") ; => nil

(let [a 1 b 2]
  (> a b)) ;false

  (defn print-and-say-hello [name]
    (print "Saying hello to " name)
    (str "Hello " name))
  (print-and-say-hello "Jeff") ;=> "Hello Jeff" (prints "Saying hello to Jeff")
;or let does same

; Use the threading macros (-> and ->>) to express transformations of
; data more clearly.
(->
   {:a 1 :b 2}
   (assoc :c 3)
   (dissoc :b))
;{:a 1, :c 3}

(->>
   (range 10)
   (map inc)
   (filter odd?)
   (into []))
;[1 3 5 7 9]

;Learning about threading macros
(as-> [1 2 3] input
  (map inc input)
  (nth input 2)
  (conj [4 5 6] input [8 9 10]))

  ; map inc input adds one to each
  ; nth input takes the number in the second position
  ; then conj (conjoin) all together

  ;modules

  (use 'clojure.set)
  (intersection #{1 2 3} #{2 3 4}) ;{3 2}

;Return a set that is the first set without elements of the remaining sets

  (difference #{1 2 3} #{2 3 4}) ;#{1}

  ; Use require to import a module
  (require 'clojure.string)

  (clojure.string/blank? "") ;true
  (clojure.string/blank? "t") ;false

;require clojure.string module and rename as str
  (require '[clojure.string :as str])
(str/replace "This is a test." #"[a-o]" str/upper-case) ;"THIs Is A tEst."

;[a-o] regex everything between & including those letters

; Use import to load a java module
(import java.util.Date)

(Date.) ; #inst "2017-09-02T00:36:04.502-00:00"

(. (Date.) getTime) ;1504312624282

(.getTime (Date.)) ;same results

; Use / to call static methods
(System/currentTimeMillis) ; <a timestamp> (system is always present)

(import java.util.Calendar)

(import java.util.Calendar)
(doto (Calendar/getInstance)
  (.set 2000 1 1 0 0 0)
  .getTime) ; => A Date. set to 2000-01-01 00:00:00


  ; STM
  ;;;;;;;;;;;;;;;;;

  ; Software Transactional Memory is the mechanism clojure uses to handle
  ; persistent state. There are a few constructs in clojure that use this.

  ; An atom is the simplest. Pass it an initial value
  (def my-atom (atom {:h 7}))
  my-atom
  ;{:h 7}
  (swap! my-atom assoc :a 1)
  (swap! my-atom assoc :b 2)
  my-atom
  ;{:h 7, :b 2, :a 1}
  @my-atom


;Counter
(def counter (atom 0))

(def counter (atom 0))
(defn inc-counter []
  (swap! counter inc))

(inc-counter);1
(inc-counter);2
@counter ; 2

;****
;figure this out
(inc-counter);1
(* 3 (inc-counter)) ; 6
@counter ;2
