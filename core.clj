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
"Hello Steve"
