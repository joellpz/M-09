package UF2ThreadsAndThings.Semaforo.CompteEstalvi;

public class CompteEstalvi {
    float saldo;

    public CompteEstalvi(float saldo) {
        this.saldo = saldo;
    }

    public void ingresar(float n){
         setSaldo(this.saldo+n);
    }

    public void retirar(float n){
        setSaldo(this.saldo-n);
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
