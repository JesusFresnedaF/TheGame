/*
    Clase Bola
 */
package thegamecontroller.objetos;

import thegamecontroller.dtos.VelocityDTO;
import thegamecontroller.dtos.AcelerationDTO;
import thegamecontroller.dtos.CoordinatesDTO;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import localgame.LocalModel;
import thegamecontroller.dtos.VODState;

/**
 *
 * @author jesus
 */
public class Bola extends VODynamic implements Runnable{

    //ATRIBUTOS
    public static final int VIDA_MAX = 200;
    public static final int VIDA_MIN = 0;

    private float masa;
    private int radio, diam;
    private float vida;
    private Color color;

    private static int contador = 0;
    private int id;

    private transient LocalModel model;

    //CONSTRUCTORES
    public Bola(LocalModel model) {
        super();
        this.model = model;
        this.vida = (int) (Math.random() * VIDA_MAX) + 1;
        this.masa = 70;
        this.radio = 30;
        this.diam = 2 * radio;
        this.color = Color.BLUE;
        contador++;
        this.id = contador;
    }

    public Bola(CoordinatesDTO posicion, VelocityDTO velocidad, AcelerationDTO aceleracion, float masa, int radio) {
        super(VODState.ALIVE, velocidad, aceleracion, posicion);
        this.vida = (int) (Math.random() * VIDA_MAX) + 1;
        this.masa = masa;
        this.radio = radio;
        this.diam = 2 * radio;
        this.model = null;
        contador++;
        this.id = contador;
    }

    //--------------------------------------------------
    //ID
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    //--------------------------------------------------
    //DTOs
    public CoordinatesDTO getPosicion() {
        return super.getPosition();
    }

    public synchronized void setPosicion(CoordinatesDTO posicion) {
        super.setPosition(posicion);
    }

    public VelocityDTO getVelocidad() {
        return super.getVelocity();
    }

    public synchronized void setVelocidad(VelocityDTO velocidad) {
        super.setVelocity(velocidad);
    }

    public AcelerationDTO getAceleracion() {
        return super.getAcceleration();
    }

    public synchronized void setAceleracion(AcelerationDTO aceleracion) {
        super.setAcceleration(aceleracion);
    }

    //--------------------------------------------------
    //POSICIONES
    public float getPosX() {
        return super.getPosition().getX();
    }

    public float getPosY() {
        return super.getPosition().getY();
    }

    public synchronized void setPosX(int x) {
        super.getPosition().setX(x);
    }

    public synchronized void setPosY(int y) {
        super.getPosition().setY(y);
    }

    //--------------------------------------------------
    //VELOCIDAD
    public float getVelX() {
        return super.getVelocity().getVelocidadX();
    }

    public float getVelY() {
        return super.getVelocity().getVelocidadY();
    }

    public synchronized void setVelX(float velX) {
        super.getVelocity().setVelocidadX(velX);
    }

    public synchronized void setVelY(float velY) {
        super.getVelocity().setVelocidadY(velY);
    }

    //--------------------------------------------------
    //ACELERACION
    public double getAccX() {
        return super.getAcceleration().getAceleracionX();
    }

    public double getAccY() {
        return super.getAcceleration().getAceleracionY();
    }

    public synchronized void setAccX(int accX) {
        super.getAcceleration().setAceleracionX(accX);
    }

    public synchronized void setAccY(int accY) {
        super.getAcceleration().setAceleracionY(accY);
    }

    //--------------------------------------------------
    //MASA
    public float getMasa() {
        return masa;
    }

    public synchronized void setMasa(float masa) {
        this.masa = masa;
    }

    //--------------------------------------------------
    //RADIO
    public int getRadio() {
        return radio;
    }

    public synchronized void setRadio(int radio) {
        this.radio = radio;
    }

    public int getDiam() {
        return diam;
    }

    public synchronized void setDiam(int diam) {
        this.diam = diam;
    }
    //--------------------------------------------------
    //COLOR

    public Color getColor() {
        return color;
    }

    public synchronized void setColor(Color color) {
        this.color = color;
    }

    //--------------------------------------------------
    //VIDA
    public float getVida() {
        return vida;
    }

    public synchronized void setVida(float vida) {
        this.vida = vida;
    }

    //--------------------------------------------------
    //METODOS
    //comprueba si una bola choca con otra
    public boolean seSuperponeCon(Bola otraBola) {
        boolean impacto;
        float distanciaCentros = calcularDistanciaEntreCentros(this.getPosicion(), otraBola.getPosicion());
        int sumaRadios = this.radio + otraBola.radio;
        impacto = distanciaCentros <= sumaRadios;
        return impacto;
    }

    private float calcularDistanciaEntreCentros(CoordinatesDTO posicion1, CoordinatesDTO posicion2) {
        float distanciaX = posicion2.getX() - posicion1.getX();
        float distanciaY = posicion2.getY() - posicion1.getY();

        int distanciaEntreCentros = (int) Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);

        // Utilizamos el teorema de Pitágoras para calcular la distancia entre los centros
        return distanciaEntreCentros;
    }

    @Override
    public void pintar(Graphics g) {
        g.setColor(this.color);
        g.fillOval((int) this.getPosX() - this.getRadio(),
                (int) this.getPosY() - this.getRadio(),
                this.getDiam(), this.getDiam());
    }

    @Override
    public synchronized void mover() {
        super.setPosition(model.calcNewPositions(this));
    }

    public synchronized void cambiarColor() {
        int r, g, b;
        r = (int) (Math.random() * 255);
        g = (int) (Math.random() * 255);
        b = (int) (Math.random() * 255);
        this.color = new Color(r, g, b);
    }

    @Override
    public synchronized void rebotarX() {
        super.getVelocity().setVelocidadX(-super.getVelocity().getVelocidadX());
    }

    @Override
    public synchronized void rebotarY() {
        super.getVelocity().setVelocidadY(-super.getVelocity().getVelocidadY());
    }

    @Override
    public void sendBola() {
        System.out.println("Send bola");
        explode();
        this.model.sendBola(this);
    }

    @Override
    public synchronized void damage(Bola bola) {
        // le hago daño a la otra bola
        if ((this.getVida() - bola.getMasa()) <= VIDA_MIN) {
            this.explode();
        } else {
            this.setVida(bola.getVida() - this.getMasa());
            this.rebotarX();
            this.rebotarY();
        }
    }

    @Override
    public synchronized void explode() {
        this.setVida(VIDA_MIN);
        super.setState(VODState.DEAD);
    }

    public boolean isViva() {
        return (super.getState() == VODState.ALIVE);
    }

    @Override
    public void run() {
        while (this.isViva()) {
            try {
                mover();
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean isPing(){
        return this.id == -1;
    }

    @Override
    public String toString() {
        return  "masa=" + masa + ",radio=" + radio + ",diam=" + diam + 
                ",vida=" + vida + ",posX="+ getPosition().getX()+ 
                ",posY="+getPosY()+",velX="+getVelX()+ ",velY="+getVelY()+
                ",accX"+getAccX()+",accY="+getAccY();
    }

    public void setModel(LocalModel model) {
        this.model = model;
    }

    public LocalModel getModel() {
        return this.model;
    }
    

}
