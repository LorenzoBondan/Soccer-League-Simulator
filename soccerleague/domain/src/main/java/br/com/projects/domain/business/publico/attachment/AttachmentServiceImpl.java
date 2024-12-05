package br.com.projects.domain.business.publico.attachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.attachment.api.AttachmentService;
import br.com.projects.domain.business.publico.attachment.spi.CrudAttachment;
import br.com.projects.domain.metadata.DomainService;

@DomainService
public class AttachmentServiceImpl implements AttachmentService {

    private final CrudAttachment crudAttachment;

    public AttachmentServiceImpl(CrudAttachment crudAttachment) {
        this.crudAttachment = crudAttachment;
    }

    @Override
    public Paged<DAttachment> find(PageableRequest request) {
        return crudAttachment.findAll(request);
    }

    @Override
    public DAttachment find(Integer id) {
        return crudAttachment.find(id);
    }

    @Override
    public DAttachment insert(DAttachment domain) {
        domain.validate();
        return crudAttachment.insert(domain);
    }

    @Override
    public DAttachment update(DAttachment domain) {
        domain.validate();
        return crudAttachment.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudAttachment.delete(id);
    }
}
