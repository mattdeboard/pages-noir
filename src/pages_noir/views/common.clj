(ns pages-noir.views.common
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpartial layout [& content]
            (html5
              [:head
               [:title "pages-noir"]
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))
