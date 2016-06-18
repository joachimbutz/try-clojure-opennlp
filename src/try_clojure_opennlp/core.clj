(ns try-clojure-opennlp.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(foo "Hi")

(use 'clojure.pprint) ; just for this documentation
(use 'opennlp.nlp)
(use 'instaparse.abnf)
(use 'opennlp.treebank) ; treebank chunking, parsing and linking lives here

;; http://opennlp.sourceforge.net/models-1.5/

(def get-sentences (make-sentence-detector "models/en-sent.bin"))
(def tokenize (make-tokenizer "models/en-token.bin"))
(def detokenize (make-detokenizer "models/english-detokenizer.xml"))
(def pos-tag (make-pos-tagger "models/en-pos-maxent.bin"))
(def name-find (make-name-finder "models/en-ner-person.bin"))
(def chunker (make-treebank-chunker "models/en-chunker.bin"))

;;(def tokenize (make-tokenizer my-tokenizer-model)) ;; etc, etc

(pprint (get-sentences "First sentence. Second sentence? Here is another one. And so on and so forth - you get the idea..."))

(detokenize ["Mr.", "Smith", "gave", "a", "car", "to", "his", "son", "on", "Friday"])

(pprint (pos-tag (tokenize "Mr. Smith gave a car to his son on Friday.")))

(name-find (tokenize "My name is Lee, not John."))

(pprint (chunker (pos-tag (tokenize "The override system is meant to deactivate the accelerator when the brake pedal is pressed."))))

