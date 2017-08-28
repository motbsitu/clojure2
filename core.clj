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
