package com.deb.naturalsignapp.screens

class greedyAlgo() {

    val denomList: MutableList<Int> = mutableListOf()
    val imageList: MutableList<String> = mutableListOf()
    val fixedDenom: List<Int> = listOf(5, 10, 20, 50, 100, 200, 500, 1000)
    val fixedImage: List<String> = listOf("five_note", "ten_note", "twenty_note", "fifty_note", "hnote", "tnote", "fnote", "thousand_note")

    fun splitAmount(amount: Int){
        var workAmount = amount

        while (workAmount>0){
            val itr = fixedDenom.listIterator()
            var amnt: Int
            var denom = 0

            while (itr.hasNext()){
                amnt = itr.next()
                if (amnt <= workAmount){
                    denom = amnt
                }
            }
            workAmount -= denom
            denomList.add(denom)
        }

    }

    fun getDenomList(amount: Int): MutableList<Int> {
        splitAmount(amount)
        return denomList
    }

    fun generateImageList(amount: Int): MutableList<String> {
        splitAmount(amount)
        val itr = denomList.listIterator()
        while (itr.hasNext()){
            val number=itr.next()

            when(number){
                5 -> imageList.add(fixedImage[0])
                10 -> imageList.add(fixedImage[1])
                20 -> imageList.add(fixedImage[2])
                50 -> imageList.add(fixedImage[3])
                100 -> imageList.add(fixedImage[4])
                200 -> imageList.add(fixedImage[5])
                500 -> imageList.add(fixedImage[6])
                1000 -> imageList.add(fixedImage[7])
                
            }
        }

    return imageList

    }


}