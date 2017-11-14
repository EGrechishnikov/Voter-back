package by.grechishnikov.dao;

import by.grechishnikov.dto.MyVote;
import by.grechishnikov.entity.Vote;

import java.util.List;

public interface IVoteDAO extends IBaseDAO<Vote>{
    List<MyVote> getAllVotesForUser(int userId);
}
