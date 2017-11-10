package by.grechishnikov.service;

import by.grechishnikov.entity.Voting;

import java.util.List;

public interface IVotingService extends IBaseService<Voting> {
    List<Voting> getAll(int page);
}
