package helpers
import static groovyx.gpars.GParsPool.*
class Anagrama {
	def benchmark = { closure ->
		start = System.currentTimeMillis()
		closure.call()
		now = System.currentTimeMillis()
		now - start
	}
	def start
	def now
	
	def dir
	def palabra_original
	def palabras_permutadas = new String[71937]
	def c
	def bots
	def nBots
	def fileContents
	def palabras
	def counter
	def comenzar () {
		this.dir = "src/diccionario_venezuela"
		//palabra_original= "luciernaga"
		//this.dir = "src/alternativo"
		//this.palabras_permutadas = new Array
				this.palabra_original = "teologia"
		this.c = new Consola()
		this.bots = []
		this.nBots = 4		

		this.fileContents = new File(this.dir).getText('UTF-8')

		this.palabras = this.fileContents.split() //Arreglo
		//this.palabras = this.fileContents.tokenize() //Lista
		this.counter=0
		this.palabra_original.split('')[1..this.palabra_original.length()].permutations().each {palabra_nueva-> 
			this.palabras_permutadas[this.counter+1]=palabra_nueva.join();
				this.counter=this.counter+1;
				}



		//palabras_permutadas.each {pp->
		//	palabras.each {p->
		//		if (p == pp){
		//			println p + " es un anagrama"
		//		}
		//	}
		//}

		def duration = benchmark {
			nBots.times{
				bots[it] = new Bot(palabras,it,c,palabra_original,palabras_permutadas[it*(palabras_permutadas.size()/nBots)..((1+it)*(palabras_permutadas.size()/nBots)-1)])
			}
			//bots[0] = new Bot(palabras[0],0,c,palabra_original,palabras_permutadas)
			//bots[1] = new Bot(palabras,1,c,palabra_original,palabras_permutadas)
			//bots[2] = new Bot(palabras,2,c,palabra_original,palabras_permutadas)
			//bots[3] = new Bot(palabras,3,c,palabra_original,palabras_permutadas)


			withPool {
				bots.eachParallel {
					it.start()
				}
			}
		}
		println "execution took ${duration} ms"		
	}
	static a
	static main(args) {
		a = new Anagrama()
		a.comenzar()
	}

}
