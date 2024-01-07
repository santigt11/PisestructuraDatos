package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.MallaCurricular;

public class MallaArchivos extends AdaptadorDao<MallaCurricular> {

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

    @Override
    public Integer persist(MallaCurricular obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
