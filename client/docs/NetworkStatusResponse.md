

# NetworkStatusResponse

NetworkStatusResponse contains basic information about the node's view of a blockchain network. If a Rosetta implementation prunes historical state, it should populate the optional `oldest_block_identifier` field with the oldest block available to query. If this is not populated, it is assumed that the `genesis_block_identifier` is the oldest queryable block.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currentBlockIdentifier** | [**BlockIdentifier**](BlockIdentifier.md) |  | 
**currentBlockTimestamp** | [**java.sql.Timestamp**](java.sql.Timestamp.md) |  | 
**genesisBlockIdentifier** | [**BlockIdentifier**](BlockIdentifier.md) |  | 
**oldestBlockIdentifier** | [**BlockIdentifier**](BlockIdentifier.md) |  |  [optional]
**peers** | [**List&lt;Peer&gt;**](Peer.md) |  | 



