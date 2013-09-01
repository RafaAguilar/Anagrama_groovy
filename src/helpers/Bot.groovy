package helpers

class Bot {

	def mi_diccionario
	def mi_id
	def consola
	def palabra
	def permutaciones

	Bot (dict, id, out, word, perm){
		this.mi_id = id
		this.mi_diccionario = dict
		this.consola = out
		this.palabra = word
		this.permutaciones = perm
	}

	def start() {
		mi_diccionario.each{d->
			permutaciones.each{p->
//				consola.salida("p->" + p + " -- d->" + d + "=> bot:" + mi_id)
				if ( p == d){
					consola.salida("Anagrama encontrado ->" + d +" por bot:" + mi_id)
				}
				
			}
		}
	}

}
