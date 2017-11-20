package by.grechishnikov.dao;

import by.grechishnikov.dto.MyVoteDTO;
import by.grechishnikov.entity.Vote;

import java.util.List;

public interface IVoteDAO extends IBaseDAO<Vote>{
    List<MyVoteDTO> getAllVotesForUser(int userId);
}
