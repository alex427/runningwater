package alex.day0323

import scala.collection.mutable.ArrayBuffer

//import scala.collection.immutable

//only 80days remained, be hard.
object Dog {

  def main(args: Array[String]) {

      val aa = (1 to 10).map(_ * 10)
      println("aa:  "+aa)  //aa:  Vector(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

      val fun = (x: Int) => {
          x * 20
      }

      for (a <- aa.map(fun(_))) {
          print(a+"   ")
      }

      for (a <- aa) {
          print(a+"   ")
      }

      //yield 生成工具
      //val yy = for(a <- aa) yield a*3
      for (a <- aa)
          yield {
              if (a % 3 == 0)
                  print("aa " + a+"  ")
          }

      //定义数组
      val arr = new Array[Int](10)
      arr(0)=2
      arr(1)=3

      val arr2 = Array(10,12,3,4,5)

      //定义一个数组
      val arr6 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
      //需求: 将偶数取出乘以10, 返回一个新的数组
      //方法1: 在for循环中加入判断
      val newarr = for (e <- arr6 if e % 2 == 0) yield e * 10
      println(newarr.toBuffer)
      //方法2: filter(函数)
      val newarr2 = arr6.filter( x=>x%2==0 ).map(x=>x*10)
      println(newarr2.toBuffer)

      //ArrayBuffer(2, 3, 0, 0, 0, 0, 0, 0, 0, 0)
      //ArrayBuffer(10, 12, 3, 4, 5)
      print(arr.toBuffer)
      println(arr2.toBuffer)

      val arr3 = new Array[String](5)
      arr3(0)="endorphin"
      arr3(0)="dophamin"
      arr3(1)="cortisol"
      println(arr3.toBuffer)  //ArrayBuffer(dophamin, cortisol, null, null, null)

      //定义ArrayBuffer
      //长度参数对ArrayBuffer有什么作用?
      val arraybuffer = new ArrayBuffer[Int](3)
      arraybuffer.append(3)
      print(arraybuffer) //ArrayBuffer(3)
      arraybuffer.+=(5)
      print(arraybuffer) //ArrayBuffer(3,5)
      arraybuffer.+=(5).+=(33).+=(22).+=(2).+=(6)
      print(arraybuffer) // ArrayBuffer(3, 5, 5, 33, 22, 2, 6)
      // def insert(index: Int, elems: A*) { insertAll(index, elems) }
      arraybuffer.insert(0,17)
      print(arraybuffer)  //ArrayBuffer(17, 3, 5, 5, 33, 22, 2, 6)
      arraybuffer.insert(1,13,55,23,7)
      print(arraybuffer)  //ArrayBuffer(17, 13, 55, 23, 7, 3, 5, 5, 33, 22, 2, 6)

      val buf = new ArrayBuffer[Int]()
      buf.append(3)

      val buf2 = new ArrayBuffer[Tuple2[Int,Int]]()
      val tuple = (3,9)
      buf2.append(tuple)

      //数组遍历
      //scala的数组遍历方式, 非常多.
      //注意 until, to 关键字的使用, 可以通过他们轻松地获取一个Range
      //map方法的参数, 是一个函数
      for(ele <- arr3){
          println(ele)
      }

      arr3.map( (x:String)=>print(x+"   ")  )

      println(0 until arr3.length)             //Range(0, 1, 2, 3, 4)
      println((0 to arr3.length).reverse)   //Range(4, 3, 2, 1, 0)

      for(i <- (0 until arr3.length)){
          if(arr3(i)==null){
              arr3(i)="hormone"
          }
          println(arr3(i))
      }
      //函数写法的变形
      arr3.map( (x:String)=>print(x) )
      arr3.map( x=>print(x) )
      arr3.map( print(_) )

      //过滤
      arr2.filter( (x:Int)=>x%2==0 )
        .map(println(_))

      arr2.filter( x=>x%2==0 )
        .map( println(_) )

      arr2.filter( _%2==0 )
        .map( println(_) )


      //排序
      val arr5 = Array(160,12,73,84,53,22,133,355,33,12,25)
      val narr = arr5.sortBy(x=>x>x)
      val narr1 = arr5.sortWith((x,y)=>x>y)


      //zip操作: 对两个数组实现合并操作, 合并之后, 形成kv结构, 集合类型为Tuple2
      //zip操作的调用者的元素为key, 参数的元素为value
      //如果参与zip操作的两个集合的长度不一样, 则scala自然取长度短的集合的长度
      val ziparr = arr5.zip(arr2)
      print(ziparr.getClass)
      println(ziparr.toBuffer)


      //调用函数
      val res = work(2,7,func_a)

  }
    //function
    //it is parameterized.
    //定义函数
    val func_cup = (x:Int,y:Int)=>(x+y)

    val func_a = (x:Int, y:Int)=> {x+y}

    //method can has a function as param
    def work(x:Int, y:Int, func: (Int,Int)=>Int):Int = {
        func(x,y)
    }






















}
