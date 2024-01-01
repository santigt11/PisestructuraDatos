package controlador.Academico;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.MallaCurricular;

public class MallaArchivos extends DaoImplement<MallaCurricular> {

    private DynamicList<MallaCurricular> mallas;
    private MallaCurricular malla;

    public MallaArchivos() {
        super(MallaCurricular.class);
    }

    public DynamicList<MallaCurricular> getMallas() {
        mallas = all();
        return mallas;
    }

    public void setMallas(DynamicList<MallaCurricular> mallas) {
        this.mallas = mallas;
    }

    public MallaCurricular getMalla() {
        if (malla == null) {
            malla = new MallaCurricular();
        }
        return malla;
    }

    public void setMalla(MallaCurricular malla) {
        this.malla = malla;
    }

    public Boolean persist() {
        malla.setId(all().getLength());
        return persist(malla);
    }
}
