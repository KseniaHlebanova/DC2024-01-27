﻿using Publisher.Entity.Db;
using Publisher.Entity.DTO.RequestTO;
using Publisher.Entity.DTO.ResponseTO;
using Publisher.Service.Interface.Common;

namespace Publisher.Service.Interface
{
    public interface ICreatorService : ICrudService<Creator, CreatorRequestTO, CreatorResponseTO>
    {
        Task<CreatorResponseTO> GetByNewsID(int newsId);
    }
}
