package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas doblemente ligadas de cadenas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos.</p>
 */
public class ListaCadena{

    /**
     * Clase Nodo para uso interno de la clase ListaCadena.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private String elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(String elemento) {
	    this.elemento=elemento;
	    anterior=null;
	    siguiente=null;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return(anterior);
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return(siguiente);
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public String get() {
            return(elemento);
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return(longitud);
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        if(cabeza==null){
	    return(true);
	}else{
	    return(false);
	}
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(String elemento) {
	Nodo nodo= new Nodo(elemento);
	if(this.esVacia()){
	    cabeza=nodo;
	    rabo=cabeza;
	}else{
	    rabo.siguiente=nodo;
	    nodo.anterior=rabo;
	    rabo=nodo;	    
      	}	
	longitud=longitud+1;

    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(String elemento) {
         Nodo nodo= new Nodo(elemento);
	if(this.esVacia()){
	    cabeza=nodo;
	    rabo=cabeza;
	}else{
	    cabeza.anterior=nodo;
	    nodo.siguiente=cabeza;
	    cabeza=nodo;	    
      	}	
	longitud=longitud+1;
    }
    /**
      Devuelve el primer nodo de la lista cuyo elemento sea la cadena elemento.
      Este método es privado ya que servira como auxiliar de otros métodos de esta clase.
      En caso de que la lista no contenga ningún nodo con el elemento buscado el método regresara null.
     */
    private Nodo buscaNodo(String elemento, Nodo cabezaContador){
	if(this.esVacia()){
	    return null;
	}else if(cabezaContador == null){
	    return null;
	}else{
	    if(cabezaContador.elemento.equals(elemento)){
		return cabezaContador;
	    }else{
		cabezaContador=cabezaContador.siguiente;
		return this.buscaNodo(elemento, cabezaContador);
	    }
	}
    }
                

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     */
    public void inserta(int i, String elemento){
	if(i<=0){
	    this.agregaInicio(elemento);
	    return;
	}else if (longitud <= i){
	    this.agregaFinal(elemento);
	    return;
	}else{
	    Nodo iesimo = this.indiceNodo1(i,cabeza);
	    Nodo nuevo = new Nodo(elemento);
	    nuevo.anterior=iesimo.anterior;
	    nuevo.siguiente=iesimo;
	    iesimo.anterior.siguiente=nuevo;
	    iesimo.anterior=nuevo;
	    longitud=longitud+1;
	    return;
	}
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(String elemento) {
        if(this.esVacia()){
	    return;
	}else{
	    Nodo resultado =this.buscaNodo(elemento,cabeza);
	    if(resultado==null){
		return;
	    }else if(resultado.equals(cabeza)){
		this.eliminaPrimero();
		return;
	    }else if(resultado.equals(rabo)){
		this.eliminaUltimo();
		return;
	    }else{
		resultado.anterior.siguiente=resultado.siguiente;
		resultado.siguiente.anterior=resultado.anterior;
		resultado=null;
		longitud=longitud-1;
	    }
	    
	}
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaPrimero() {
	if(this.esVacia()){
	    return null;
	}else if(longitud ==1){
	    String primero = cabeza.elemento;
	    cabeza=null;
	    rabo = null;
	    longitud=longitud-1;
	    return(primero);
	}else{
	    String primero = cabeza.elemento;
	    cabeza.siguiente.anterior=null;
	    cabeza=cabeza.siguiente;
	    longitud= longitud-1;
	    return(primero);
	}	
    }
    

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaUltimo() {
	if(this.esVacia()){
	    return(null);
	}else if(longitud == 1){
	    String ultimo = rabo.elemento;
	    rabo = null;
	    cabeza = null;
	    longitud--;
	    return(ultimo);
	}else{
	    String ultimo =rabo.elemento;
	    rabo.anterior.siguiente=null;
	    rabo=rabo.anterior;
	    longitud=longitud-1;
	    return(ultimo);
	}
	
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(String elemento) {
        if(this.esVacia()){
	    return false;
	}else if(this.buscaNodo(elemento,cabeza) == null){
	    return false;
	}else{
	    return true;
	}
    }

    /** Metodo que recibe un noto contador y una lista vacia y entrega una lista nuevo con los 
	elementos de los nodos	anteriores al nodo que recibio pero en orden opuesto*/
    private ListaCadena reversaRecursiva(Nodo contador, ListaCadena nueva){
	if(this.esVacia()){
	     return nueva;
	}else if(this.longitud==1){
	    nueva.agregaFinal(contador.elemento);
	    return nueva;
	}else if(contador.equals(cabeza)){
	    nueva.agregaFinal(contador.elemento);
	    return nueva;
	}else{
	    nueva.agregaFinal(contador.elemento);
	    return this.reversaRecursiva(contador.anterior,nueva);
	}		
    }
    

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaCadena reversa(){
	ListaCadena nueva = new ListaCadena();
	return this.reversaRecursiva(this.rabo,nueva);
    }
    
    /** Metodo que recibe un nodo contador y una lista vacia  y entrega una lista  con los mismos
     elementos de la lista que mando a llamar el método*/
    private ListaCadena copiaRecursiva(Nodo contador, ListaCadena nueva){
	if(this.esVacia()){
	     return nueva;
	}else if(this.longitud==1){
	    nueva.agregaFinal(contador.elemento);
	    return nueva;
	}else if(contador.equals(rabo)){
	    nueva.agregaFinal(contador.elemento);
	    return nueva;
	}else{
	    nueva.agregaFinal(contador.elemento);
	    return this.copiaRecursiva(contador.siguiente,nueva);
	}		
    }


    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaCadena copia() {
        ListaCadena nueva= new ListaCadena();
	return this.copiaRecursiva(this.cabeza,nueva);
    }

    private void limpiaRecursiva(Nodo contador){
	if(this.esVacia()){
	    return;
	}else if(contador == null){
	    return;	
	}else{
	    contador=contador.siguiente;
	    this.eliminaPrimero();
	    this.limpiaRecursiva(contador);	
	}
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {
        this.limpiaRecursiva(cabeza);
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getPrimero() {
        if(this.esVacia() ){
	    return(null);
	}else{
	    return(cabeza.elemento);
	}
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getUltimo() {
        if(this.esVacia() ){
	    return(null);
	}else{
	    return(rabo.elemento);
	}
    }


    /** Este método recibe un índice y el nodo cabeza y nos regresa el nodo en dicho
        índice o null si el indice es menor que 0 o mayor que la longitud de la
        cadena */
    private Nodo indiceNodo1(int indice, Nodo contador){
	if(this.esVacia()){
	    return null;
	}else if(indice < 0 || longitud <= indice){
	    return null;
	}else{
	    if(indice == 0){
		return contador;
	    }else{
		return this.indiceNodo1(indice-1,contador.siguiente);
	    }
	}
    }
    
    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista. Si el índice es menor
     * que cero o mayor o igual que el número de elementos de la lista, el
     * método regresa <tt>null</tt>.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista;
     *         <tt>null</tt> en otro caso.
     */
    public String get(int i) {
	Nodo indice = this.indiceNodo1(i,cabeza);
	if(indice==null){
	    return null;
	}else{
	    return indice.elemento;
	}
    }
	/** Metodo que recibe un nodo y el nodo cabeza y devuelve el indice del 
	    primer nodo que recibio, el método devuelve -1 si el nodo buscado
	    no se encuetra en la lista o si el nodo buscado es null
	   
	 */
    private int indiceNodo2(Nodo buscado, Nodo contador){
	if(this.esVacia()){
	    return -1;
	}else if(buscado == null){
	    return -1;
	}else{
	    if(buscado.equals(contador)){
		return 0;
	    }else{
	       	return 1+this.indiceNodo2(buscado,contador.siguiente);
	    }
	}
    }

    
    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(String elemento) {
	Nodo buscado =this.buscaNodo(elemento,cabeza);
	int indice =this.indiceNodo2(buscado,cabeza);
	return indice;
    }

    private String toStringRecursivo(String s, int i){
	 if(this.esVacia()){
	     s="[]";
	    return(s);
	}else if(longitud==1){
	     s= "["+ String.format("%s]", this.get(0));
	    return(s);
	 }else if(i <  longitud -1){
	     s += String.format("%s, ", this.get(i));
	     return this.toStringRecursivo(s,i+1);
	 }else{
	     s += String.format("%s]", this.get(longitud-1));
	     return(s);
	}
    }    
    
    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */

    
    public String toString() {
        int i=0;
	String s="[";
	return this.toStringRecursivo(s,i);
    }

    
    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return(cabeza);
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return(rabo);
    }
}
