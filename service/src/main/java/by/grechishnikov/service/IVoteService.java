package by.grechishnikov.service;

import by.grechishnikov.dto.MyVoteDTO;
import by.grechishnikov.entity.Vote;

import java.util.List;

public interface IVoteService extends IBaseService<Vote> {
    List<MyVoteDTO> getAllVotesForUser(int userId);
}
