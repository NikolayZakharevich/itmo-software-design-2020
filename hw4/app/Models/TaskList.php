<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\TaskList
 *
 * @property int                             $id
 * @property string                          $name
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|TaskList newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|TaskList newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|TaskList query()
 * @method static \Illuminate\Database\Eloquent\Builder|TaskList whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|TaskList whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|TaskList whereName($value)
 * @method static \Illuminate\Database\Eloquent\Builder|TaskList whereUpdatedAt($value)
 * @mixin \Eloquent
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\Task[] $tasks
 * @property-read int|null $tasks_count
 */
class TaskList extends Model {

    protected $table = 'task_lists';

    protected $guarded = ['id'];

    public function tasks() {
        return $this->hasMany(Task::class, 'list_id', 'id');
    }
}
