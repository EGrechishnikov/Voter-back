package by.grechishnikov.service;

import by.grechishnikov.dto.MyVote;
import by.grechishnikov.entity.Vote;

import java.util.List;

public interface IVoteService extends IBaseService<Vote> {
    List<MyVote> getAllVotesForUser(int userId);
}
