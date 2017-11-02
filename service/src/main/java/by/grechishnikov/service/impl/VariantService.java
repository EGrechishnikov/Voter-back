package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IVariantDAO;
import by.grechishnikov.entity.Variant;
import by.grechishnikov.service.IVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VariantService implements IVariantService {
    private IVariantDAO variantDAO;

    @Autowired
    public VariantService(IVariantDAO variantDAO) {
        this.variantDAO = variantDAO;
    }

    @Override
    public void saveOrUpdate(Variant variant) {
        variantDAO.saveOrUpdate(variant);
    }

    @Override
    public Variant get(int id) {
        return variantDAO.get(id);
    }

    @Override
    public void delete(Variant variant) {
        variantDAO.delete(variant);
    }

    @Override
    public List<Variant> getAll() {
        return variantDAO.getAll();
    }
}
