Feature: As an existing user I am able to add/update/delete tracks for the existing and new playlists

  Scenario: New tracks can be added to the existing playlist test for the current user
    Given get number of tracks for the current user in playlist testPlay
    When I add any new tracks to the existing playlist test for the current user with success
    Then number of tracks for the playlist testPlay is higher to default

  Scenario: Any track (existed) can be deleted from the existing playlist test for the current user
    Given get number of tracks for the current user in playlist testPlay
    When delete any track from the playlist testPlay
    Then number of tracks for the playlist testPlay is less to default

  Scenario Outline: New track with invalid id <ids> can not be added to the new playlist
    Given I create new playlist with name <playlistName> for the current user
    When I add <ids> new tracks to the existing playlist <playlistName> for the current user with error
    Then number of tracks for the playlist <playlistName> is equal to 0
    Examples:
      | ids     | playlistName |
      | test    | testPlayX    |
      | 000000  | ZeroPlay     |
      | &68b^*( | SymbolPlay   |

  @ToImplement
  Scenario: Error message if deleting not existing track from playlist
    Given I create new playlist with name Testing for the current user
    When remove track 4iV5W9uYEdYUVa79Axb7Rh from the playlist with error
    Then number of tracks for the playlist Testing is equal to 0

  @ToImplement
  Scenario: Reorder tracks in the existing playlist
    Given I add 4iV5W9uYEdYUVa79Axb7Rh new tracks to the existing playlist test for the current user with success
    When I reorder tracks with next parameters:
      | range_start   | 2 |
      | insert_before | 1 |
      | range_length  | 2 |
    Then make sure that tracks reordered successfully
