package alex.day0323

object Cheetah {

    def main(args: Array[String]): Unit = {
        val arr = Array("the rescue ship then towed cheetah to the port with the shark still on deck.",
            "imagine you’re a cheetah and you see two juicy gazelles grazing in the grass",
            "among the 40 choices are a bengal tiger, lion, panther, cheetah or leopard cub",
            "ten years ago, apple introduced the first major release of its os x operating system, cheetah",
            "and at dawn, kudu, with their great corkscrew horns, or cheetah, will stand in the high grass beyond the fence, peering in")

        val words = arr.map(x=>x.split(" "))
        val fmap = words.flatten.map(remover(_))
        val pairs = fmap.map(x=>(x,1))
        val keygroup = pairs.groupBy( _._1 )
        val res = keygroup.mapValues(x => x.map(y => y._2).reduce((a,b)=>a+b))

        println(res.toBuffer+"   "+res.getClass)
    }

    //函数: 清洗标点符号
    val remover = (x:String)=>{
        if(x.endsWith(",") | x.endsWith(".")){
            x.substring(0,x.length-1)
        } else {
            x
        }
    }


}
