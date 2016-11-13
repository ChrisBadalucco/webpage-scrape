class scrape {

    static void main(String... args) {
        def timer = new Timer()
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            void run() {
                new URL('http://emotiva.com/products/amplifiers/xpa-5').eachLine { line ->
                    def matcher = (line =~ 'value="Add to cart"')
                    if (matcher.find()) {
                        scrape.printSuccess() //button = matcher[0][1]
                        timer.cancel()
                    }
                }
                scrape.printFailure()
            }
        }, 10, 3600000) // .1 second delay, 60 minutes between runs
    }

    static void printSuccess() {
        println '*'
        println '*'
        println '*'
        println '********************************'
        println '* SUCCESS GO BUY THE DAMN AMP'
        println '********************************'
        println '*'
        println '*'
        println '*'
    }

    static void printFailure() {
        println 'out of stock - retrying...'
    }
}