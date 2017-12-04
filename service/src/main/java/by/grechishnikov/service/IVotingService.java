package by.grechishnikov.service;

import by.grechishnikov.dto.ChosenVariantDTO;
import by.grechishnikov.dto.VotingsDTO;
import by.grechishnikov.entity.Voting;

import java.util.List;

/**
 * Service for Voting entity
 */
public interface IVotingService extends IBaseService<Voting> {
    /**
     * Create voting by Voting record
     *
     * @param voting - dto
     */
    void createVoting(Voting voting);

    /**
     * Create voting with image by JSON
     *
     * @param json     - JSON with DTO object
     * @param bytes    - image
     * @param fileName - image name
     */
    void createVoting(String json, byte[] bytes, String fileName);

    /**
     * Create voting without image by JSON
     *
     * @param json - JSON with DTO object
     */
    void createVoting(String json);

    /**
     * Get voting list from DB with the count of all voting records
     *
     * @param page - number of page
     * @return - dto that includes a list and a records count
     */
    VotingsDTO getAll(int page);

    /**
     * Get all votes for voting
     *
     * @param votingId - id in DB
     * @return - list
     */
    List<ChosenVariantDTO> getAllVotesForVoting(int votingId);
}
